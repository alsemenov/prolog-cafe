package com.googlecode.prolog_cafe.lang;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * The superclass of classes for Prolog exceptions.<br>
 *
 * @author Mutsunori Banbara (banbara@kobe-u.ac.jp)
 * @author Naoyuki Tamura (tamura@kobe-u.ac.jp)
 * @version 1.0
 */
public abstract class PrologException extends RuntimeException {

    /** Constructs a new Prolog exception. */
    public PrologException() {}

    public PrologException(String s) {
      super(s);
    }

    /** Returns the message term of this object. */
    abstract public Term getMessageTerm();

    private Operation[] prologStackElement;

    public Operation[] getPrologStackTrace() {
    	return prologStackElement.clone();
    }

    void setPrologStackTrace(Operation[] stack){
    	this.prologStackElement = stack;
    }

	@Override
	public void printStackTrace(PrintStream s) {
		super.printStackTrace(s);
		if (prologStackElement!=null && prologStackElement.length>0){
			s.println("Prolog stack trace:");
			for (Operation o: prologStackElement){
				s.println("\tat "+o.getClass().getPackage().getName()+":"+o);
			}
		}
	}

	@Override
	public void printStackTrace(PrintWriter s) {
		super.printStackTrace(s);
		if (prologStackElement!=null && prologStackElement.length>0){
			s.println("Prolog stack trace:");
			for (Operation o: prologStackElement){
				s.println("\tat "+o.getClass().getPackage().getName()+":"+o);
			}
		}
	}
}
