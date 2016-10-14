JCC = javac

JFLAGS = -g

default: CalcSpending.class GrepReader.class GmailAPIcall.class

CalcSpending.class: CalcSpending.java
	$(JCC) $(JFLAGS) CalcSpending.java GrepReader.java GmailAPIcall.java

GrepReader.class: GrepReader.java
	$(JCC) $(JFLAGS) GrepReader.java

GmailAPIcall.class: GmailAPIcall.java
	$(JCC) $(JFLAGS) GmailAPIcall.java

clean:
	$(RM) *.class