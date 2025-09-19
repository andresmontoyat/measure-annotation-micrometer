# Structure resources folder

This document consolidates the **standard resource files** every project must include.  
It serves as a template to generate README, SECURITY, CONTRIBUTING, LICENSE, CHANGELOG, SUPPORT, and `.gitignore` with context and usage guidance.

---

## 1) Source layout for git project

Generate resources files:

***README***

Generate README.md with the next content:
```
# {project_name}
================

## Getting Started
Basic instructions for building, running, and using the project.

### Prerequisites
List of stack technologies

### Usage
Examples of running the application.

#### Environment Variables
List required environment variables.

## Contributing
See [CONTRIBUTING.md](CONTRIBUTING.md).

## Versioning
This project uses [Semantic Versioning](https://semver.org/).

## Authors
* Your Name – [your-site.com](https://your-site.com)

## License
See [LICENSE.md](LICENSE.md).

```

***SECURITY***

Generate SECURITY.md with the next content:
```
# Security Policies and Procedures
==================================

This document outlines security procedures and general policies for the `standard`
project.

* [Reporting a Bug](#reporting-a-bug)
* [Disclosure Policy](#disclosure-policy)
* [Comments on this Policy](#comments-on-this-policy)

## Reporting a Bug

The `standard` team and community take all security bugs in `standard` seriously.
Thank you for improving the security of `standard`. We appreciate your efforts and
responsible disclosure and will make every effort to acknowledge your
contributions.

Report security bugs by emailing the lead maintainer at security@codehunters.io.

The lead maintainer will acknowledge your email within 48 hours, and will send a
more detailed response within 48 hours indicating the next steps in handling
your report. After the initial reply to your report, the security team will
endeavor to keep you informed of the progress towards a fix and full
announcement, and may ask for additional information or guidance.

Report security bugs in third-party modules to the person or team maintaining
the module.

## Disclosure Policy

When the security team receives a security bug report, they will assign it to a
primary handler. This person will coordinate the fix and release process,
involving the following steps:

* Confirm the problem and determine the affected versions.
* Audit code to find any potential similar problems.
* Prepare fixes for all releases still under maintenance. These fixes will be
  released as fast as possible to npm.

## Comments on this Policy

If you have suggestions on how this process could be improved please submit a
pull request.
```

***CONTRIBUTING***

Generate CONTRIBUTING.md with the next content:
```
# Contributing
==============

[fork]: https://github.com/ORG/REPO/fork
[pr]: https://github.com/ORG/REPO/compare

When contributing to this repository, please first discuss the change you wish to make via issue,
email, or any other method with the owners of this repository before making a change. 

Please note we have a code of conduct, please follow it in all your interactions with the project.

## Pull Request Process

1. Check the issues or open a new one
2. Fork this repository
3. Create your feature branch: `git checkout -b feature/my-new-feature`
4. Commit your changes: `git commit -am 'Add some feature'`
5. Push to the branch: `git push origin feature/my-new-feature`
6. Submit a pull request linked to the issue 1. :D

## Our Pledge

We pledge to make our community welcoming, safe, and equitable for all.

We are committed to fostering an environment that respects and promotes the dignity, rights, and contributions of all individuals, regardless of characteristics including race, ethnicity, caste, color, age, physical characteristics, neurodiversity, disability, sex or gender, gender identity or expression, sexual orientation, language, philosophy or religion, national or social origin, socio-economic position, level of education, or other status. The same privileges of participation are extended to everyone who participates in good faith and in accordance with this Covenant.


## Encouraged Behaviors

While acknowledging differences in social norms, we all strive to meet our community's expectations for positive behavior. We also understand that our words and actions may be interpreted differently than we intend based on culture, background, or native language.

With these considerations in mind, we agree to behave mindfully toward each other and act in ways that center our shared values, including:

1. Respecting the **purpose of our community**, our activities, and our ways of gathering.
2. Engaging **kindly and honestly** with others.
3. Respecting **different viewpoints** and experiences.
4. **Taking responsibility** for our actions and contributions.
5. Gracefully giving and accepting **constructive feedback**.
6. Committing to **repairing harm** when it occurs.
7. Behaving in other ways that promote and sustain the **well-being of our community**.


## Restricted Behaviors

We agree to restrict the following behaviors in our community. Instances, threats, and promotion of these behaviors are violations of this Code of Conduct.

1. **Harassment.** Violating explicitly expressed boundaries or engaging in unnecessary personal attention after any clear request to stop.
2. **Character attacks.** Making insulting, demeaning, or pejorative comments directed at a community member or group of people.
3. **Stereotyping or discrimination.** Characterizing anyone’s personality or behavior on the basis of immutable identities or traits.
4. **Sexualization.** Behaving in a way that would generally be considered inappropriately intimate in the context or purpose of the community.
5. **Violating confidentiality**. Sharing or acting on someone's personal or private information without their permission.
6. **Endangerment.** Causing, encouraging, or threatening violence or other harm toward any person or group.
7. Behaving in other ways that **threaten the well-being** of our community.

### Other Restrictions

1. **Misleading identity.** Impersonating someone else for any reason, or pretending to be someone else to evade enforcement actions.
2. **Failing to credit sources.** Not properly crediting the sources of content you contribute.
3. **Promotional materials**. Sharing marketing or other commercial content in a way that is outside the norms of the community.
4. **Irresponsible communication.** Failing to responsibly present content which includes, links or describes any other restricted behaviors.


## Reporting an Issue

Tensions can occur between community members even when they are trying their best to collaborate. Not every conflict represents a code of conduct violation, and this Code of Conduct reinforces encouraged behaviors and norms that can help avoid conflicts and minimize harm.

When an incident does occur, it is important to report it promptly. To report a possible violation, **[NOTE: describe your means of reporting here.]**

Community Moderators take reports of violations seriously and will make every effort to respond in a timely manner. They will investigate all reports of code of conduct violations, reviewing messages, logs, and recordings, or interviewing witnesses and other participants. Community Moderators will keep investigation and enforcement actions as transparent as possible while prioritizing safety and confidentiality. In order to honor these values, enforcement actions are carried out in private with the involved parties, but communicating to the whole community may be part of a mutually agreed upon resolution.


## Addressing and Repairing Harm

**[NOTE: The remedies and repairs outlined below are suggestions based on best practices in code of conduct enforcement. If your community has its own established enforcement process, be sure to edit this section to describe your own policies.]**

If an investigation by the Community Moderators finds that this Code of Conduct has been violated, the following enforcement ladder may be used to determine how best to repair harm, based on the incident's impact on the individuals involved and the community as a whole. Depending on the severity of a violation, lower rungs on the ladder may be skipped.

1) Warning
   1) Event: A violation involving a single incident or series of incidents.
   2) Consequence: A private, written warning from the Community Moderators.
   3) Repair: Examples of repair include a private written apology, acknowledgement of responsibility, and seeking clarification on expectations.
2) Temporarily Limited Activities
   1) Event: A repeated incidence of a violation that previously resulted in a warning, or the first incidence of a more serious violation.
   2) Consequence: A private, written warning with a time-limited cooldown period designed to underscore the seriousness of the situation and give the community members involved time to process the incident. The cooldown period may be limited to particular communication channels or interactions with particular community members.
   3) Repair: Examples of repair may include making an apology, using the cooldown period to reflect on actions and impact, and being thoughtful about re-entering community spaces after the period is over.
3) Temporary Suspension
   1) Event: A pattern of repeated violation which the Community Moderators have tried to address with warnings, or a single serious violation.
   2) Consequence: A private written warning with conditions for return from suspension. In general, temporary suspensions give the person being suspended time to reflect upon their behavior and possible corrective actions.
   3) Repair: Examples of repair include respecting the spirit of the suspension, meeting the specified conditions for return, and being thoughtful about how to reintegrate with the community when the suspension is lifted.
4) Permanent Ban
   1) Event: A pattern of repeated code of conduct violations that other steps on the ladder have failed to resolve, or a violation so serious that the Community Moderators determine there is no way to keep the community safe with this person as a member.
   2) Consequence: Access to all community spaces, tools, and communication channels is removed. In general, permanent bans should be rarely used, should have strong reasoning behind them, and should only be resorted to if working through other remedies has failed to change the behavior.
   3) Repair: There is no possible repair in cases of this severity.

This enforcement ladder is intended as a guideline. It does not limit the ability of Community Managers to use their discretion and judgment, in keeping with the best interests of our community.


## Scope

This Code of Conduct applies within all community spaces, and also applies when an individual is officially representing the community in public or other spaces. Examples of representing our community include using an official email address, posting via an official social media account, or acting as an appointed representative at an online or offline event.


## Attribution

This Code of Conduct is adapted from the Contributor Covenant, version 3.0, permanently available at [https://www.contributor-covenant.org/version/3/0/](https://www.contributor-covenant.org/version/3/0/).

Contributor Covenant is stewarded by the Organization for Ethical Source and licensed under CC BY-SA 4.0. To view a copy of this license, visit [https://creativecommons.org/licenses/by-sa/4.0/](https://creativecommons.org/licenses/by-sa/4.0/)

For answers to common questions about Contributor Covenant, see the FAQ at [https://www.contributor-covenant.org/faq](https://www.contributor-covenant.org/faq). Translations are provided at [https://www.contributor-covenant.org/translations](https://www.contributor-covenant.org/translations). Additional enforcement and community guideline resources can be found at [https://www.contributor-covenant.org/resources](https://www.contributor-covenant.org/resources). The enforcement ladder was inspired by the work of [Mozilla’s code of conduct team](https://github.com/mozilla/inclusion).
```

***LICENSE***

Generate LICENSE.md with the next content:
```
The MIT License (MIT)
=====================

Copyright © {{YEAR}} {{ORG}}

Permission is hereby granted, free of charge, to any person
obtaining a copy of this software and associated documentation
files (the “Software”), to deal in the Software without
restriction, including without limitation the rights to use,
copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the
Software is furnished to do so, subject to the following
conditions:

The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
OTHER DEALINGS IN THE SOFTWARE.
```

***CHANGELOG***

Generate CHANGELOG.md with the next content:
```
# Changelog
============

All notable changes to this project will be documented here.

## [Unreleased]
- Pending changes

## [1.0.0] - YYYY-MM-DD
### Added
- Initial release

```

***SUPPORT***

Generate SUPPORT.md with the next content:

```
# Support
==========

## Self-help
- Check README.md and CHANGELOG.md
- Search GitHub Issues

## Community
- Open a GitHub Issue for bugs/features

## Commercial
- Email: {{SUPPORT_EMAIL}}

```