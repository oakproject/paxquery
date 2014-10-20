#!/bin/bash

##########
## Configuration by the user
### set FLINK_PATH to the path to Flink directory in your system
FLINK_PATH=/Users/jalvaro/flink-0.6-incubating
### set PAXQUERY_INSTALLATION_PATH  to the path where Paxquery is installed
#PAXQUERY_INSTALLATION_PATH=""
PAXQUERY_INSTALLATION_PATH=/Users/jalvaro/paxquery-installation
#FILES_ORIGIN=/Users/jalvaro/XML/libs
FILES_ORIGIN=.
##########

# Variables
PAXQUERY_INSTALLATION_BIN=$(PAXQUERY_INSTALLATION_PATH)/bin
PAXQUERY_INSTALLATION_LIB=$(PAXQUERY_INSTALLATION_PATH)/lib
PAXQUERY_INSTALLATION_SCRIPTS=$(PAXQUERY_INSTALLATION_PATH)/scripts

#check whether paths are set
ifndef FLINK_PATH
$(error Please set the script variable FLINK_PATH to the flink instalation path. Aborting installation.)
endif
ifndef PAXQUERY_INSTALLATION_PATH
$(error Please set the script variable PAXQUERY_INSTALLATION_PATH to the path where you want PAXQuery to be installed. Aborting installation.)
endif	


#all:
#	@echo "Please use 'make install' or 'make uninstall' after manually editing the Flink and PAXQuery installation paths at the top of Makefile."

install: 
	@echo "Copying files..."
	mkdir -p $(PAXQUERY_INSTALLATION_BIN)
	mkdir -p $(PAXQUERY_INSTALLATION_LIB)
	mkdir -p $(PAXQUERY_INSTALLATION_SCRIPTS)
	cp $(FILES_ORIGIN)/paxquery-algebra-0.1.jar $(PAXQUERY_INSTALLATION_BIN)/paxquery-algebra-0.1.jar
	cp $(FILES_ORIGIN)/paxquery-translation-0.1.jar $(PAXQUERY_INSTALLATION_BIN)/paxquery-translation-0.1.jar
	cp $(FILES_ORIGIN)/paxquery-xparser-0.1.jar $(PAXQUERY_INSTALLATION_BIN)/paxquery-xparser-0.1.jar
	cp $(FILES_ORIGIN)/antlr-runtime-4.2.jar $(PAXQUERY_INSTALLATION_LIB)/antlr-runtime-4.2.jar
	cp $(FILES_ORIGIN)/json-simple-1.1.jar $(PAXQUERY_INSTALLATION_LIB)/json-simple-1.1.jar
	cp $(FILES_ORIGIN)/paxquery-common-0.1.jar $(FLINK_PATH)/lib/paxquery-common-0.1.jar
	cp $(FILES_ORIGIN)/paxquery-pact-0.1.jar $(FLINK_PATH)/lib/paxquery-pact-0.1.jar
	cp $(FILES_ORIGIN)/paxquery-run.sh $(PAXQUERY_INSTALLATION_SCRIPTS)/paxquery-run.sh
	cp $(FILES_ORIGIN)/Makefile $(PAXQUERY_INSTALLATION_SCRIPTS)/Makefile
	@echo ""
	@echo "PAXQuery installed, remember to restart Flink!."
	@echo "Have fun."
	@echo ""

uninstall: 
	@echo "Removing PAXQuery..."
	rm -rf $(PAXQUERY_INSTALLATION_BIN)
	rm -rf $(PAXQUERY_INSTALLATION_LIB)
	rm -rf $(PAXQUERY_INSTALLATION_SCRIPTS)
	rm -rf $(PAXQUERY_INSTALLATION_PATH)
	rm -f $(FLINK_PATH)/lib/paxquery-common-0.1.jar
	rm -f $(FLINK_PATH)/lib/paxquery-pact-0.1.jar
	@echo "PAXQuery removed."	
	@echo ""
