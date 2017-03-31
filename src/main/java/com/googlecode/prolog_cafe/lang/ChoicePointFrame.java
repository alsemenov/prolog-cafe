// Copyright 2011 Google Inc. All Rights Reserved.

package com.googlecode.prolog_cafe.lang;

/**
 * Choice point frame.
 *
 * @author Mutsunori Banbara (banbara@kobe-u.ac.jp)
 * @author Naoyuki Tamura (tamura@kobe-u.ac.jp)
 * @version 1.0
 */
class ChoicePointFrame {
    ChoicePointFrame prior;

    long timeStamp;
    Operation cont;  // continuation goal
    Operation bp;    // next clause
    int tr;          // trail pointer
    int b0;          // cut point
    Operation ownerPredicate;

    static ChoicePointFrame S0(Operation cont) {
      ChoicePointFrame r = new ChoicePointFrame();
      r.cont = cont;
      return r;
    }

    void restore(Prolog engine) {
      engine.cont = this.cont;
    }

    public String toString() {
    	StringBuilder sb = new StringBuilder(ChoicePointFrame.class.getName());
    	sb.append('{');
    	sb.append("time:").append(timeStamp);
    	sb.append(" cont:").append(cont);
    	sb.append(" bp:").append(bp);
    	sb.append(" tr:").append(tr);
    	sb.append(" b0:").append(b0);
    	sb.append('}');
    	return sb.toString();
    }

    static final class S1 extends ChoicePointFrame {
      private final Term areg1;

      S1(Prolog engine) {
        this.cont = engine.cont;
        this.areg1 = engine.areg1;
      }

      final void restore(Prolog engine) {
        engine.cont = this.cont;
        engine.areg1 = this.areg1;
      }
    }

    static final class S2 extends ChoicePointFrame {
      private final Term areg1, areg2;

      S2(Prolog engine) {
        this.cont = engine.cont;
        this.areg1 = engine.areg1;
        this.areg2 = engine.areg2;
      }

      final void restore(Prolog engine) {
        engine.cont = this.cont;
        engine.areg1 = this.areg1;
        engine.areg2 = this.areg2;
      }
    }

    static final class S3 extends ChoicePointFrame {
      private final Term areg1, areg2, areg3;

      S3(Prolog engine) {
        this.cont = engine.cont;
        this.areg1 = engine.areg1;
        this.areg2 = engine.areg2;
        this.areg3 = engine.areg3;
      }

      final void restore(Prolog engine) {
        engine.cont = this.cont;
        engine.areg1 = this.areg1;
        engine.areg2 = this.areg2;
        engine.areg3 = this.areg3;
      }
    }

    static final class S4 extends ChoicePointFrame {
      private final Term areg1, areg2, areg3, areg4;

      S4(Prolog engine) {
        this.cont = engine.cont;
        this.areg1 = engine.areg1;
        this.areg2 = engine.areg2;
        this.areg3 = engine.areg3;
        this.areg4 = engine.areg4;
      }

      final void restore(Prolog engine) {
        engine.cont = this.cont;
        engine.areg1 = this.areg1;
        engine.areg2 = this.areg2;
        engine.areg3 = this.areg3;
        engine.areg4 = this.areg4;
      }
    }

    static final class S5 extends ChoicePointFrame {
      private final Term areg1, areg2, areg3, areg4, areg5;

      S5(Prolog engine) {
        this.cont = engine.cont;
        this.areg1 = engine.areg1;
        this.areg2 = engine.areg2;
        this.areg3 = engine.areg3;
        this.areg4 = engine.areg4;
        this.areg5 = engine.areg5;
      }

      final void restore(Prolog engine) {
        engine.cont = this.cont;
        engine.areg1 = this.areg1;
        engine.areg2 = this.areg2;
        engine.areg3 = this.areg3;
        engine.areg4 = this.areg4;
        engine.areg5 = this.areg5;
      }
    }

    static final class S6 extends ChoicePointFrame {
      private final Term areg1, areg2, areg3, areg4, areg5, areg6;

      S6(Prolog engine) {
        this.cont = engine.cont;
        this.areg1 = engine.areg1;
        this.areg2 = engine.areg2;
        this.areg3 = engine.areg3;
        this.areg4 = engine.areg4;
        this.areg5 = engine.areg5;
        this.areg6 = engine.areg6;
      }

      final void restore(Prolog engine) {
        engine.cont = this.cont;
        engine.areg1 = this.areg1;
        engine.areg2 = this.areg2;
        engine.areg3 = this.areg3;
        engine.areg4 = this.areg4;
        engine.areg5 = this.areg5;
        engine.areg6 = this.areg6;
      }
    }

    static final class S7 extends ChoicePointFrame {
      private final Term areg1, areg2, areg3, areg4, areg5, areg6, areg7;

      S7(Prolog engine) {
        this.cont = engine.cont;
        this.areg1 = engine.areg1;
        this.areg2 = engine.areg2;
        this.areg3 = engine.areg3;
        this.areg4 = engine.areg4;
        this.areg5 = engine.areg5;
        this.areg6 = engine.areg6;
        this.areg7 = engine.areg7;
      }

      final void restore(Prolog engine) {
        engine.cont = this.cont;
        engine.areg1 = this.areg1;
        engine.areg2 = this.areg2;
        engine.areg3 = this.areg3;
        engine.areg4 = this.areg4;
        engine.areg5 = this.areg5;
        engine.areg6 = this.areg6;
        engine.areg7 = this.areg7;
      }
    }

    static class S8 extends ChoicePointFrame {
      private final Term areg1, areg2, areg3, areg4, areg5, areg6, areg7, areg8;

      S8(Prolog engine) {
        this.cont = engine.cont;
        this.areg1 = engine.areg1;
        this.areg2 = engine.areg2;
        this.areg3 = engine.areg3;
        this.areg4 = engine.areg4;
        this.areg5 = engine.areg5;
        this.areg6 = engine.areg6;
        this.areg7 = engine.areg7;
        this.areg8 = engine.areg8;
      }

      void restore(Prolog engine) {
        engine.cont = this.cont;
        engine.areg1 = this.areg1;
        engine.areg2 = this.areg2;
        engine.areg3 = this.areg3;
        engine.areg4 = this.areg4;
        engine.areg5 = this.areg5;
        engine.areg6 = this.areg6;
        engine.areg7 = this.areg7;
        engine.areg8 = this.areg8;
      }
    }

    static final class S9 extends S8 {
      private final Term[] aregs;

      S9(int arity, Prolog engine) {
        super(engine);
        aregs = new Term[arity - 8];
        System.arraycopy(engine.aregs, 0, aregs, 0, aregs.length);
      }

      final void restore(Prolog engine) {
        System.arraycopy(aregs, 0, engine.aregs, 0, aregs.length);
        super.restore(engine);
      }
    }
}
