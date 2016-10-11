JCC = javac

JFLAGS = -g

default: CalcSpending.class GrepReader.class

CalcSpending.class: CalcSpending.java
	$(JCC) $(JFLAGS) CalcSpending.java GrepReader.java

GrepReader.class: GrepReader.java
	$(JCC) $(JFLAGS) GrepReader.java

clean:
	$(RM) *.class