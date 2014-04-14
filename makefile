CC=javac
CR=java
CFLAGS=-d target -cp src
LDFLAGS=-cp
TARG=target
SOURCES=src/App.java
OBJECTS=$(SOURCES:.java=.class)
EXECUTABLE=App

all: $(SOURCES) ;
	mkdir -p $(TARG)
	$(CC) $(CFLAGS) $(SOURCES)



clean: ; rm -r $(TARG)

run: ; make
	$(CR) $(LDFLAGS) $(TARG) $(EXECUTABLE)
