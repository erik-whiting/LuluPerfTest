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
Design and Development of a Technology-Agnostic NFR Testing Framework: Introducing the framework and discussing the future of load testing in Agile software development
Erik Whiting
Soma Data
ICSIM 2022: 2022 The 5th International Conference on Software Engineering and Information Management
BibTex:
```
@inproceedings{10.1145/3520084.3520092,
author = {Whiting, Erik and Datta, Soma},
title = {Design and Development of a Technology-Agnostic NFR Testing Framework: Introducing the Framework and Discussing the Future of Load Testing in Agile Software Development},
year = {2022},
isbn = {9781450395519},
publisher = {Association for Computing Machinery},
address = {New York, NY, USA},
url = {https://doi.org/10.1145/3520084.3520092},
doi = {10.1145/3520084.3520092},
abstract = {Testing the non-functional requirements (NFR) of a system is particularly complicated and time-consuming. Challenges in this area are compounded when the system is developed under some offspring of Agile methodologies, which favor iterative development and rapid feedback from extensive testing. The authors of this paper build upon previous work investigating the common challenges and solutions cited in recent peer-reviewed research on this topic to design and build a tool consolidating many of the concepts found in this investigation. The tool is known as LuluPerfTest (LPT) and is an NFR testing framework meant to plug into continuous integration (CI) systems to run NFR tests configured with a JSON script. This allows developers and testers to build maintainable and minimally complex automated NFR test scripts. This study explains the challenges inherent in NFR testing in Agile software development and presents how LPT confronts those challenges. It aims to explain LPT and invite collaboration among other testing, verification, and validation researchers to create an open sources software (OSS) solution to the problems of NFR testing in Agile software development projects.},
booktitle = {2022 The 5th International Conference on Software Engineering and Information Management (ICSIM)},
pages = {45–50},
numpages = {6},
keywords = {performance testing, non-functional requirements, Software testing},
location = {Yokohama, Japan},
series = {ICSIM 2022}
}
```

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
