# Contributing

## Introduction
Thanks for your interest in contributing to LuluPerfTest! This project began as primarily an academic
endeavor, but we aim to benefit professionals outside of academia as well. Such projects are usually
short on contributors, so we are excited for every bit of interest that comes our way!

Additionally, open-source software provides so much benefit to our world, so even if you don't contribute
to our project, please consider being a permanent member of the OSS community.

## Functionality and Design
This section describes how LPT is designed and how it works. Part of this section is intended for potential
contributors who have not used LPT before.

### JSON Test Script
LPT works by taking in a JSON script that defines a load test with various configurations.
The script is essentially a `PerformanceTest` object that defines:
* a `name`
* `useCases`
* `monitors`
The name is simply the name for a specific test. The `useCases` are pointers to simulated
user interactions with the system under test--typically written as automation tests. The
`monitors` are a list of objects configured to watch and report upon different system
metrics.

### useCases
The `useCases` portion of the `PerformanceTest` object defined in the JSON script is an array of
individual instances of a `useCase` model. Use cases define how a user generally interacts
with a system. Here, a `useCase` object points to a file, usually called an **automation script**
that programmatically simulates a use case. LPT runs these automation scripts against the SUT
in order to simulate a realistic load against the system. The `useCase` objects are comprised
of the following attributes:
* `name`
* `script`
* `command`
* `threads`

The `name` attribute simply gives a name to the automated use case. The `script` attribute
is a relative path to the file containing the automation script, whereas the `command` attribute
is the system command needed to run that script. Finally, the `threads` attribute records how
many threads should run that script. Another way to think of `threads` is as users.

For example, suppose we have a Python script that runs a browser automation script for logging
into the system under test, a web application. If testers want LPT to simulate three users trying
to login to the system, the `useCase` configuration might look like this:
```json
"useCases": [
  {
    "name": "Login",
    "script": "./scripts/login.py",
    "command": "python",
    "threads": "3"
  }
]
```
### monitors
The `monitors` array is an array of individual instances of a `monitor` model. These instances
describe system metrics that an LPT user wants to record as part of the load test. Some examples
of system metrics are things like memory usage, available disk space, or CPU use. Additionally,
each `monitor` instance records how often the system metric is to be checked with an attribute
called `every`.

For example, if we want to check the CPU and memory use of the SUT every half second our load test runs,
the `monitors` portion of our script might look like this:
```json
"monitors": [
  { "name": "memory", "every": "500" },
  { "name": "CPU", "every": "500" }
]
```
### Under the Hood
With an understanding the JSON script's configuration, we can talk about how LPT works behind the scenes.
First, the `main` thread of LPT instantiates a `DslRunner` object with a path to the JSON script as
an input. The `DslRunner` does some basic error checking on the path to the script; if everything is
alright, `DslRunner` uses the path to instantiate its own `DslParser` object. The `DslRunner` parses the
JSON file upon instantiation and calls its `buildTest` method. This method populates the `DslRunner`
attributes `reportConfiguration`, `monitors` and `useCases`. Then, `DslRunner` calls the `DslParser`'s
`run` method, which calls the `start` method on its `monitors` and `useCases`.

The `Monitors` class is a collection of `MetricMonitor` instances. Upon instantiation, the `Monitors`
class creates Java `Thread` instances from its `metricMonitors`. This step is necessary so that LPT
can make use of multithreading, this allows LPT to gather metrics at the same time the automated use cases
are running against the SUT, giving a realistic measure of the system's performance.

Like `Monitors`, the `UseCases` class is a collection of `UseCase` instances. Unlike `Monitors` however,
the `UseCases` class does not create `Thread` instances, this is handled in the `UseCase` class. This is
because `UseCase` instances may be configured with multiple `threads`, or simulated users. Therefore,
the `UseCase` class is responsible for creating `Thread` instances. Once a `UseCase` has instantiated its
appropriate number of threads, each thread runs the designated script with the designated command. For
example, if the JSON `useCase` object was defined as:
```json
  {
    "name": "Login",
    "script": "./scripts/login.py",
    "command": "python",
    "threads": "3"
  }
```
then there would be three threads running the following shell script:
```shell script
?> python ./scripts/login.py
```

This has been a high-level overview of how LuluPerfTest works. Details such as how each `MetricMonitor`
class gathers its configured metric is beyond the scope of this section.

## Contribution Workflow

### Set Up
LPT is written in Java 11 and is tested with jUnit 4. The project manages all dependencies via
Maven in the root-level [pom.xml](pom.xml) file.

To set up a local version of LPT for contributing, simply clone the repository with:
```shell script
git clone git@github.com:erik-whiting/LuluPerfTest.git
``` 
Once you've done this, CD into the repository and run the following Maven commands:

```shell script
?> mvn validate
?> mvn compile
?> mvn test
```
This will make sure all necessary information is available, compile LPT, and then test it.
If all the tests pass, you're ready to start writing code.

### Creating a Branch and Pull Request
After setting up a local copy of the project, think of a meaningful name for the branch you're about to
make and run these commands:

`git checkout -b my-cool-branch-name`

Write your code and try to make meaningful commits. Here's an example:

```shell script
# Write some code
$> git commit -am "Add new class to a module"
# Make some more changes
$> git commit -am "Add test for new class"
 ```

Once you've finished, please run all tests and correct any new failures. To run
all tests, run the following from the terminal while in the project root:

`mvn test`

Once you've committed everything, run:

`git push origin my-cool-branch-name`

Then, head over to the [repository on Github](https://github.com/erik-whiting/LuluPerfTest)
and create your pull request against the `main` branch. That's it! Someone will review it shortly after.

### A note on testing

Pull requests without tests will most likely be rejected unless the work
you've done is minor enough that a test would be overkill. If you're an
absolute beginner, don't let this scare you! Writing tests is good practice,
and there are most likely enough tests in the [test package](src/test)
that you can figure out what you need to do :)

Keep in mind, this framework is designed to be a tool for software testing
and quality assurance, lets practice what we preach.

## Code of Conduct
Please consult our [Code of Conduct](CODE_OF_CONDUCT.md).
