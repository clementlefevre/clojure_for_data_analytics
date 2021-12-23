# Clojure for Data Analysis

A short demo on how to use the tablecloth library for data analysis.
For people used to work with R/python, it might be difficult to find the same amount of online resources for clojure.
Hereby are some code snippets i wrote that cover some generic workflow when handling tabular data :
- load a folder of Excel file, filter, select specific columns and combine them into a single Dataframe.
- query a Database and store the result into a Dataframe.

Beware that the [tablecloth](https://scicloj.github.io/tablecloth/index.html) works with Clojure >1.10


## Usage
Just clone the repo, and, with a wokring Clojure/Lein setup, cd into the `clojure_for_data_analysis` and run `lein deps`
Then open a REPL to explore one of the file :
- `excel_demo.clj`
- `db_demo.clj`


## Holy Graal on Windows
Once you are done with the Clojure scripting, you can compile your project into a binary file.

### Installation on Windows
For the lucky Windows users, you need to follow the instructions [here](https://medium.com/graalvm/using-graalvm-and-native-image-on-windows-10-9954dc071311) :

1 - install **GraalVM** and set the environment variable `GRAALVM_HOME`, `JAVA_HOME` and `PATH` correctly.

2 - install the **GraalVM native-image**

3 -  the **Visual Studio Build Tools and Windows 10 SDK**

*Bonus* : On top of that, i added 2 additional environment variables : `INCLUDE` & `LIB` by following [this.](https://programmerah.com/solved-default-native-compiler-executable-cl-exe-not-found-via-environment-variable-path-32444/)


### Create a Windows .exe from your clojure project
Once you are happy with your code, just run `lein uberjar` and you will get 2 `.jar` files in your `target folder`:  a standard `.jar` and standalone version).

Then, open the **X64 Native Tools Command Prompt for VS 2019**, cd into your target folder and run the following command :
`native-image -jar clojure-for-data-analysis-0.1.0-SNAPSHOT-standalone.jar`

If everything runs smoothly, you should then get a new `clojure-for-data-analysis-0.1.0-SNAPSHOT-standalone.exe` file in the target folder.

### Benchmark
When running the excel-demo script on 120 Files, the jar version took 50 Seconds and the .exe version 40 Seconds. But keep in mind that the **.exe** size  is 11MB an the standalone **.jar** 88MB.




## License

Eclipse Public License - v 2.0