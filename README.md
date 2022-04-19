# LuluPerfTest

## Overview
LuluPerfTest is a free and open-source technology agnostic non-functional requirements (NFR) testing framework.
The LPT framework aims to provide software engineers and other stakeholders of software quality a FOSS tool
for quickly creating and easily maintaining NFR test scripts.

## High-Level Usage
NFR test scripts in LPT leverage existing automated test scripts by allowing users to define loads
that run automated UI scripts. Users specify via a JSON script the location of test scripts, the number of
users to simulate (load), and system under test (SUT) metrics to be monitored.

Out of the box, users can monitor CPU usage, memory usage, and diskspace usage with LPT. Furthermore,
users can create custom system metric monitors by returning the desired value via the relevant shell
script for the SUT (e.g., BASH, PowerShell, etc.).

## Testing
LuluPerfTest is built with Apache Maven, so testing is as simple as running
```shell script
> mvn test
```
from the command line. This will run all the tests located in the `test` package.

However, since this tool is a testing framework on its own, testing of the project
must evolve to include running a testbed application in which to test this application. The architecture of such
testing has not yet been designed as this will required a large undertaking of effort. Any suggestions, advice, or
contributions are welcome in this area.

## Contributing
Contributors of all kinds are welcome to LuluPerfTest and all contributions, big or small, are appreciated.
Please visit the [contribution guide](CONTRIBUTING.md). We welcome all kinds of contributions, not just code.

## Citing
If you use this tool in your research, please cite our paper:
Erik Whiting and Soma Datta. 2022. Design and Development of a Technology-Agnostic NFR Testing Framework: Introducing the framework and discussing the future of load testing in Agile software development. In 2022 The 5th International Conference on Software Engineering and Information Management (ICSIM) (ICSIM 2022). Association for Computing Machinery, New York, NY, USA, 45–50. DOI:https://doi-org.uhcl.idm.oclc.org/10.1145/3520084.3520092

## Academic Goals
While the pragmatic goal of LuluPerfTest is ultimately to alleviate challenges associated with non-functional
requirements testing in software engineering, LPT has an academic goal as well. Noting the various challenges
and many proposed solutions present in academic research meant to address NFR testing challenges, the authors
aim to help facilitate and consolidate further research into this area. As such, all academics and professionals
are welcome and encouraged to contribute to this repository either with code, suggestions, issues, documentation
or general correspondence. LPT is meant to be a gift, albeit a modest one, to the academic and professional
software engineering and computer science communities.

## Questions
For any questions, please contact Erik Whiting via email: erik@erikwhiting.com
