[<img src="https://raw.githubusercontent.com/ForgeRock/forgerock-logo-dev/master/Logo-fr-dev.png" align="right" width="220px"/>](https://developer.forgerock.com/)

| |Current Status|
|---|---|
|Build|[![Build Status](https://img.shields.io/endpoint.svg?url=https%3A%2F%2Factions-badge.atrox.dev%2FOpenBankingToolkit%2Fopenbanking-clients%2Fbadge%3Fref%3Dmaster&style=flat)](https://actions-badge.atrox.dev/OpenBankingToolkit/openbanking-clients/goto?ref=master)|
|Code coverage|[![codecov](https://codecov.io/gh/OpenBankingToolkit/openbanking-clients/branch/master/graph/badge.svg)](https://codecov.io/gh/OpenBankingToolkit/openbanking-clients)
|Bintray|[![Bintray](https://img.shields.io/bintray/v/openbanking-toolkit/OpenBankingToolkit/openbanking-clients.svg?maxAge=2592000)](https://bintray.com/openbanking-toolkit/OpenBankingToolkit/openbanking-clients)|
|License|![license](https://img.shields.io/github/license/ACRA/acra.svg)|

**_This repository is part of the Open Banking Tool kit. If you just landed to that repository looking for our tool kit,_
_we recommend having a first read to_ https://github.com/OpenBankingToolkit/openbanking-toolkit**

ForgeRock OpenBanking Clients
========================

All the applications in our Open Banking eco-system, offers APIs. We do call those via some Java code and we therefore needs
to build a Java SDK corresponding to those APIs.
Instead of having each applications generating their version of the JWKMS client for example, we group all the clients 
in this repo by simplicity.

In a long term, an approach based on a swagger would be more appropriate and more elegant. For the time being and with the
time line we got, this approach is our best attempt to avoid code duplication and circle dependencies (jar and repos as well).