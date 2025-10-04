# JQF Demo with Maven

A demonstration project showcasing JQF + Zest.

This project implements a testcase that reveals potential issues in Apache Commons Collections' PatriciaTrie
implementation through fuzzing. JQF (https://github.com/rohanpadhye/JQF) is used to automatically generate test
inputs that may expose bugs in the mapping between regular Maps and Tries.
   
Additional handwritten testcases are given as demonstrations of JQF.

**TODO: write some cool testcases!**

# Usage

To run JQF on this class, run the following script in a project's root directory:
`mvn jqf:fuzz -f pom.xml -Dclass=<className> -Dmethod="<methodName>"`

For example, the [PatriciaTrieTest](src/main/java/org/example/demos/PatriciaTrieTest.java) test class may be fuzzed as follows:
`mvn jqf:fuzz -f pom.xml -Dclass=PatriciaTrieTest -Dmethod=testMap2Trie`

## Class Annotation

To enable JQF testing for a class, annotate it with `@RunWith(JQF.class)`:
```java
import edu.berkeley.cs.jqf.fuzz.JQF;

@RunWith(JQF.class)
public class PatriciaTrieTest { ... }
```

Each method with a JQF test must also be annotated with `@Fuzz`:
```java
import edu.berkeley.cs.jqf.fuzz.Fuzz;

@Fuzz
public void testMap2Trie(Map<String, Integer> map, String key) { ... }
```

# Installation

```
git clone https://github.com/JasonFeng365/JQFDemoMaven
cd JQFDemoMaven
```

## Maven Installation Guide

This demonstration uses Apache Maven as a build tool.
This guide provides instructions for installing Maven on different operating systems.

### Prerequisites

- Java Development Kit (JDK) 8 or higher installed
- JAVA_HOME environment variable properly set

### Windows Installation

Instructions are taken from https://maven.apache.org/install.html.

1. Download Maven from [Apache Maven website](https://maven.apache.org/download.cgi)
2. Extract the downloaded archive (e.g., `apache-maven-3.9.11-bin.zip`)
3. Add Maven's bin directory to the System PATH:
	- Right-click "This PC" > Properties > Advanced system settings
	- Click "Environment Variables"
	- Under System Variables, find and select "Path"
	- Click "New" and add the path to Maven's bin directory (e.g., `C:\Program Files\apache-maven-3.9.11\bin`)
4. Verify installation by opening a command prompt and typing:
   ```
   mvn -version
   ```

### macOS Installation

Choose one of these methods to install Maven:

- Using Homebrew:
   ```bash
   brew install maven
   ```

- Using SDKMAN!:
   ```bash
   sdk install maven
   ```

- Using MacPorts:
   ```bash
   sudo port install maven3
   ```

### Linux Installation

Choose one of these methods to install Maven:

- Using APT (Debian/Ubuntu):
   ```bash
   sudo apt install maven
   ```

- Using DNF (Fedora):
   ```bash
   sudo dnf install maven
   ```

- Using YUM (RHEL/CentOS):
   ```bash
   sudo yum install maven
   ```

# Sample Output

```
Semantic Fuzzing with Zest
--------------------------

Test name:            PatriciaTrieTest#testMap2Trie
Instrumentation:      Janala
Results directory:    C:\Users\jason\IdeaProjects\JQFDemoMaven\target\fuzz-results\PatriciaTrieTest\testMap2Trie
Elapsed time:         14s (no time limit)
Number of executions: 35,483 (no trial limit)
Valid inputs:         15,396 (43.39%)
Cycles completed:     6
Unique failures:      1
Queue size:           54 (9 favored last cycle)
Current parent input: 29 (favored) {579/860 mutations}
Execution speed:      3,108/sec now | 2,468/sec overall
Total coverage:       115 branches (0.18% of map)
Valid coverage:       115 branches (0.18% of map
```

A file will be generated in [target/fuzz-results](target/fuzz-results) with the class name, containing data about tests run and tests failed.

**TODO: how to read and interpret these files?**

# Troubleshooting

**TODO**

Citations
https://rohan.padhye.org/files/jqf-issta19.pdf