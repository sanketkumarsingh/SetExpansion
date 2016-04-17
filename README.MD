Maven have to be installed in the system to build and run this program.

To run the program, import the project as maven project in Eclipse. Build the project using the command: mvn compile and then:

1) Run TestStaticSeisa.java using the command: mvn exec:java -Dexec.mainClass="setexpansion.util.TestStaticSeisa"
2) Run  TestDynamicSeisa.java using the command: mvn exec:java -Dexec.mainClass="setexpansion.util.TestDynamicSeisa"

Data files, having 7000 list should be put in the 'SplitSeisa' folder. The file name should be 'data_aa' 
The two folders seisa_Test_Set and wiki_test_Set contain data_aa file which correspond to sample file from Web list and Wiki list dataset.
