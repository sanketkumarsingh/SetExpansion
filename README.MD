This project contains implementation of SIESA algorithm and our enhancements to it.

<b>Configuration requirement to run:<b>

Maven have to be installed in the system to build and run this program.

Build the project using the command (cd to Project Folder): 
run mvn compile, after that:

1) Run TestStaticSeisa.java using the command: mvn exec:java -Dexec.mainClass="setexpansion.util.TestStaticSeisa"
2) RunTestDynamicSeisa.java using the command: mvn exec:java -Dexec.mainClass="setexpansion.util.TestDynamicSeisa"

Data files, having 7000 list should be put in the 'SplitSeisa' folder. The file name should be 'data_aa' 
The two folders seisa_Test_Set and wiki_test_Set contain data_aa file which correspond to sample file from Web list and Wiki list dataset.
