JCC = javac

JFLAGS = -g

default: CalcSpending.class

CalcSpending.class: CalcSpending.java
	$(JCC) $(JFLAGS) CalcSpending.java

clean:
	$(RM) *.class