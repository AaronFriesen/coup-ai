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
	cp -r images target
	$(CC) $(CFLAGS) $(SOURCES)



clean: ; rm -r $(TARG)

run: ; make
	$(CR) $(LDFLAGS) $(TARG) $(EXECUTABLE)
