package com.googlecode.prolog_cafe.lang;

import java.util.Collections;
import java.util.Iterator;

/**
 * Variable.<br>
 * The <code>VariableTerm</code> class represents a logical variable.<br>
 * For example,
 * <pre>
 *   Term t = new VariableTerm();
 * </pre>
 *
 * @author Mutsunori Banbara (banbara@kobe-u.ac.jp)
 * @author Naoyuki Tamura (tamura@kobe-u.ac.jp)
 * @version 1.0
 */
public class VariableTerm extends Term implements Undoable {
    /** Holds a term to which this variable is bound. Initial value is <code>this</code> (self-reference). */
    private Term val;
    /** A CPF time stamp when this object is newly constructed. */
    private long timeStamp;

    /** Constructs a new logical variable so that
     * the <code>timeStamp</code> field is set to <code>Long.MIN_VALUE</code>.
     */
    public VariableTerm() {
	val = this;
    timeStamp = Long.MIN_VALUE;
    }

    /** Constructs a new logical variable so that
     * the <code>timeStamp</code> field is set to the current value of
     * <code>CPFTimeStamp</code> of the specified Prolog engine.
     * @param engine Current Prolog engine.
     * @see Prolog#getCPFTimeStamp
     */
    public VariableTerm(Prolog engine) {
	val = this;
	timeStamp = engine.getCPFTimeStamp();
    }

    /** Returns a string representation of this object.*/
    protected String variableName() { return "_" + Integer.toHexString(hashCode()).toUpperCase(); }

    /* Term */
    /** 
     * Checks whether the argument term is unified with this one.
     * If this is an unbound variable, the <code>unify</code> method binds this to 
     * the dereferenced value of argument term: <code>bind(t.dereference(), trail)</code>,
     * and returns <code>true</code>.
     * Otherwise, it returns a <code>boolean</code> whose value is <code>val.unify(t, trail)</code>.
     * @param t the term to be unified with.
     * @param trail Trail Stack.
     * @return <code>true</code> if succeeds, otherwise <code>false</code>.
     * @see #val
     * @see #bind(Term,Trail)
     * @see Trail
     */
	public final boolean unify(Term t, Trail trail) {
        Term x = this;
        Term z;
        while ((x instanceof VariableTerm) && (z=((VariableTerm)x).val)!=x){
            x = z;
        }
		if (x instanceof VariableTerm){
			((VariableTerm) x).bind(t.dereference(), trail);
			return true;
		} else {
			return x.unify(t, trail);
		}
	}

    /** 
     * Binds this variable to a given term. 
     * And pushs this variable to trail stack if necessary. 
     * @param t a term to be bound.
     * @param trail Trail Stack
     * @see Trail
     */
	public final void bind(Term t, Trail trail) {
		if (t instanceof VariableTerm) {
			VariableTerm v = (VariableTerm) t;
			if (v.timeStamp >= this.timeStamp) {
				v.val = this;
				if (v.timeStamp < trail.timeStamp){
					trail.push(v);
				}
				return;
			}
		}

		val = t;
		if (timeStamp < trail.timeStamp){
			trail.push(this);
		}
	}

    /** 
     * Checks whether this object is convertible with the given Java class type 
     * if this variable is unbound.
     * Otherwise, returns the value of <code>val.convertible(type)</code>.
     * @param type the Java class type to compare with.
     * @return <code>true</code> if this (or dereferenced term) is 
     * convertible with <code>type</code>. Otherwise <code>false</code>.
     * @see #val
     */
    public final boolean convertible(Class type) {
	if (val != this)
	    return val.convertible(type);
	return convertible(this.getClass(), type);
    }

    /** 
     * Returns a copy of this object if unbound variable.
     * Otherwise, returns the value of <code>val.copy(engine)</code>.
     * @see #val
     */
	protected Term copy(Prolog engine) {
		Term t = this;
		while ((t instanceof VariableTerm) && ((VariableTerm) t).val != t) {
			t = ((VariableTerm) t).val;
		}
		if (t instanceof VariableTerm) {
			VariableTerm co = engine.copyHash.get(t);
			if (co == null) {
				co = new VariableTerm();
				engine.copyHash.put((VariableTerm) t, co);
			}
			return co;
		} else {
			return t.copy(engine);
		}
	}

    public final Term dereference() {
        Term t = this;
        Term z;
        while ((t instanceof VariableTerm) && (z=((VariableTerm)t).val)!=t){
            t = z;
        }
        return t;
    }

    public final boolean isGround() {
	if (val != this)
	    return val.isGround();
	return false;
    }

    public final String name() {
    if (val == this)
      return "";
    return val.dereference().name();
    }

    /** 
     * Returns <code>this</code> if this variable is unbound.
     * Otherwise, returns a Java object that corresponds to the dereferenced term:
     * <code>val.toJava()</code>.
     * @return a Java object defined in <em>Prolog Cafe interoperability with Java</em>.
     * @see #val
     */
    public Object toJava() { 
	if (val != this)
	    return val.toJava();
	return this;
    }

    /**
     * Returns a quoted string representation of this term if unbound.
     * Otherwise, returns the value of dereferenced term:
     * <code>val.toQuotedString()</code>
     * @see #val
     */
    @Override
    public String toQuotedString() {
		Term t = this;
		while ((t instanceof VariableTerm) && ((VariableTerm) t).val != t) {
			t = ((VariableTerm) t).val;
		}
		if (t instanceof VariableTerm) {
			return variableName();
		} else {
			return t.toQuotedString();
		}
    }
    /**
     * Adds a quoted string representation of this term if unbound.
     * Otherwise, adds the value of dereferenced term:
     * <code>val.toQuotedString(sb)</code>
     * @see #val
     */
    @Override
    public void toQuotedString(StringBuilder sb) {
		Term t = this;
		while ((t instanceof VariableTerm) && ((VariableTerm) t).val != t) {
			t = ((VariableTerm) t).val;
		}
		if (t instanceof VariableTerm) {
			sb.append(variableName());
		} else {
			t.toQuotedString(sb);
		} 
    }
    /* Object */
    /**
     * Checks <em>term equality</em> of two terms.
     * This method returns a <code>boolean</code> whose value is
     * (<code>this == obj</code>) if this variable is unbound.
     * Otherwise, it returns the value of <code>val.equals(obj)</code>.
     * @param obj the object to compare with. This must be dereferenced.
     * @return <code>true</code> if this (or dereferenced term) is the same as the argument;
     * <code>false</code> otherwise.
     * @see #val
     * @see #compareTo
    */
    public boolean equals(Object obj) {
	if(val != this)
	    return val.equals(obj);
	if (! (obj instanceof VariableTerm)) // ???
	    return false; //???
	return this == obj;
    }

    /**
     * Returns a string representation of this term if unbound.
     * Otherwise, returns the value of dereferenced term:
     * <code>val.toString()</code>
     * @see #val
     */
    @Override
    public String toString() {
		Term t = this;
		while ((t instanceof VariableTerm) && ((VariableTerm) t).val != t) {
			t = ((VariableTerm) t).val;
		}
		if (t instanceof VariableTerm) {
			return variableName();
		} else {
			return t.toString();
		}
    }
    /**
     * Adds a string representation of this term if unbound.
     * Otherwise, adds the value of dereferenced term:
     * <code>val.toString()</code>
     * @see #val
     */
    @Override
    public void toString(StringBuilder sb) {
		Term t = this;
		while ((t instanceof VariableTerm) && ((VariableTerm) t).val != t) {
			t = ((VariableTerm) t).val;
		}
		if (t instanceof VariableTerm) {
			sb.append(variableName());
		} else {
			t.toString(sb);
		} 
    }
    
    /**
     * If unbound returns empty iterator, otherwise returns the value's iterator.
     */
    @Override
    public Iterator<Term> iterator(){
		Term t = this;
		while ((t instanceof VariableTerm) && ((VariableTerm) t).val != t) {
			t = ((VariableTerm) t).val;
		}
		if (t instanceof VariableTerm) {
			return Collections.emptyIterator();
		} else {
			return t.iterator();
		}   	
    }

    /* Undoable */
    public void undo() { val = this; }

    /* Comparable */
    /** 
     * Compares two terms in <em>Prolog standard order of terms</em>.<br>
     * It is noted that <code>t1.compareTo(t2) == 0</code> has the same
     * <code>boolean</code> value as <code>t1.equals(t2)</code>.
     * @param anotherTerm the term to compare with. It must be dereferenced.
     * @return the value <code>0</code> if two terms are identical; 
     * a value less than <code>0</code> if this term is <em>before</em> the <code>anotherTerm</code>;
     * and a value greater than <code>0</code> if this term is <em>after</em> the <code>anotherTerm</code>.
     */
    public int compareTo(Term anotherTerm) { // anotherTerm must be dereferenced.
	if(val != this)
	    return val.compareTo(anotherTerm);
	if (! anotherTerm.isVariable())
	    return BEFORE;
	if (this == anotherTerm) 
	    return EQUAL;
	int x = this.hashCode() - ((VariableTerm)anotherTerm).hashCode();
	if (x != 0)
	    return x;
	throw new InternalException("VariableTerm is not unique");
    }

	@Override
	public final boolean isImmutable() {
		return false;
	}
}
