# JCC = javac

# JFLAGS = -g

# JCLASSDIR = helperfunctions

# default: CalcSpending.class GmailAPIcall.class

# CalcSpending.class: CalcSpending.java
# 	$(JCC) $(JFLAGS) CalcSpending.java 
# 	$(JCC) -d $(JCLASSDIR)/. $(JFLAGS) GmailAPIcall.java

# # GrepReader.class: GrepReader.java
# # 	$(JCC) $(JFLAGS) GrepReader.java

# GmailAPIcall.class: GmailAPIcall.java
# 	$(JCC) $(JFLAGS) /helperfunctions/GmailAPIcall.java

# clean:
# 	$(RM) *.class

JFLAGS = -g
JC = javac
JCLASSDIR = helperfunctions
JVM = java
FILE = 

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	CalcSpending.java \
	helperfunctions/GmailAPIcall.java \

MAIN = Main

default: classes

classes: $(CLASSES:.java=.class)

run: classes #$(MAIN).class
	$(JVM) $(MAIN)
