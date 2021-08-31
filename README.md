# LuluPerfTest

## Overview
LuluPerfTest is a free and open-source technology agnostic non-functional requirements testing framework.
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
TODO

## Contributing
TODO

## Citing
LuluPerfTest will be presented at ICSIM 2022. After the proceedings are published, this section will note
the paper to be cited. If you use this software, please cite the paper rather than the GitHub link.

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
