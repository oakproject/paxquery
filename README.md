# PAXQuery

PAXQuery is an XQuery processor built on top of the [Apache Flink](http://flink.apache.org/) platform, previously known as <a href="http://stratosphere.eu/" target="_blank">Stratosphere</a>. It automatically parallelizes queries over large collections of XML documents by translating XQuery into the PACT model used by Apache Flink.

After the user inputs the XQuery query, the engine builds an equivalent tree of algebraic operators that works on nested tuples. The set of operators includes navigation, group by, aggregation, selection, projection, and many others.

Once the tree is built and optimized, the engine compiles it into a PACT plan consisting of <em>implicit parallel</em> operators such as Map, Reduce, Match, CoGroup, or Cross. The result is given to the Apache Flink platform, which is responsible for the PACT plan optimization and its parallel execution e.g. over HDFS or the local filesystem.

##Current status

Currently PAXQuery is in a pre-alpha state. We support a subset of XQuery that can be found in the following ANTLR 4.2 files: [XQuery.g4](http://github.com/stratosphere/paxquery/blob/master/paxquery-xparser/src/main/java/fr/inria/oak/paxquery/xparser/XQuery.g4), [XPath.g4](https://github.com/stratosphere/paxquery/blob/master/paxquery-xparser/src/main/java/fr/inria/oak/paxquery/xparser/XPath.g4), and [XLexer.g4](https://github.com/stratosphere/paxquery/blob/master/paxquery-xparser/src/main/java/fr/inria/oak/paxquery/xparser/XLexer.g4). Although our algebra supports group-by and multiple levels of nesting, the translation from XQuery to it has not been completed yet. If you would like to get involved, send us a message!

##More information
http://cloak.saclay.inria.fr/research/paxquery

##Installing PAXQuery
First of all, Apache Flink should be installed in your cluster.

Pull the code from Github. You will need Maven for dependency resolution and jar generation.

1 - After generating the jar files with Maven create a directory with any name, for example /home/user/installer, and place inside it the following generated jar files:

- paxquery-algebra-0.1.jar
- paxquery-translation-0.1.jar
- paxquery-xparser-0.1.jar
- paxquery-common-0.1.jar
- paxquery-pact-0.1.jar

2 - Place also the following files into the installer folder:

- antlr-runtime-4.2.jar (get it [here](http://www.antlr.org/download/antlr-runtime-4.2.jar))
- json-simple-1.1.jar (get it [here](https://json-simple.googlecode.com/files/json_simple-1.1.jar))

3 - Copy the file [paxquery-xparser/src/main/scripts/Makefile](http://github.com/stratosphere/paxquery/blob/master/paxquery-xparser/src/main/scripts/Makefile) into your favourite directory. Then change the following section to match to your installer folder and Apache Flink installation:

```
##########
## Configuration by the user
### set FLINK_PATH to the path to Flink directory in your system
FLINK_PATH=
### set PAXQUERY_INSTALLATION_PATH  to the path where Paxquery should be installed
PAXQUERY_INSTALLATION_PATH=
### set FILES_ORIGIN to your installer folder
FILES_ORIGIN=
##########
```

4 - Run make install

Now PAXQuery is installed in your file system.

You may also want to install the Dot diagram (contained in [Graphviz](http://www.graphviz.org/)) generation program in case you want to obtain nice graphics for the query execution plans.

##Running PAXQuery

Queries are written into text files. Graphs indicating the different intermediate logical and PACT execution plans are optionally drawn with the dot program.

1 - Open ```$PAXQUERY_INSTALLATION_PATH/scripts/paxquery-run.sh```. Set the following section according to your installation:

```
##########
## Configuration by the user
### set FLINK_PATH to the path to Flink directory in your system
FLINK_PATH=
### set PAXQUERY_INSTALLATION_PATH  to the path where Paxquery is installed
PAXQUERY_INSTALLATION_PATH=
##########
```
2 - Run

```
paxquery-run.sh <query_file> <output_file> <degree_parallelism>
```

or alternatively

```
paxquery-run.sh <query_file> <output_file> <degree_parallelism> drawtrees <diagrams_output_folder>
```
