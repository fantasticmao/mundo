# Mundo

[![Actions Status](https://github.com/fantasticmao/mundo/workflows/ci/badge.svg)](https://github.com/fantasticmao/mundo/actions)
![JDK Version](https://img.shields.io/badge/JDK-21%2B-blue)
[![Maven Central](https://img.shields.io/maven-central/v/cn.fantasticmao.mundo/mundo-all.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22cn.fantasticmao.mundo%22)
[![image](https://img.shields.io/badge/license-MIT-green.svg)](https://github.com/fantasticmao/mundo/blob/master/LICENSE)

> The naming of Mundo was inspired by a champion in the League of Legends who was always beaten:
> the madman of Zaun - Dr. Mundo.

Mundo is a tiny and out-of-the-box (also maybe useless) component for business programming. It
provides some common business implementation and programming conventions for
[Spring Boot](https://spring.io/projects/spring-boot) applications. Its main content comes from some
personal requirements that I meet in my daily study and work, so you probably don't need it.

## Features

### mundo-core

- [x] `cn.fantasticmao.mundo.core.util.*` provides some common util classes

### mundo-data

- [x] `AbstractEntity` defines common attributes in Database Entity types
- [x] `@RoutingSeed` and `RoutingDataSource` supports route strategy on datasource level
- [x] `IdGenerator` provides the implementation of the distributed ID algorithm

### mundo-web

- [x] `GeneralControllerAdvice` provides a global exception handling

## Feedback

- [GitHub Issues](https://github.com/fantasticmao/mundo/issues/)
- [Telegram](https://t.me/fantasticmao)
- [WeChat](https://fantasticmao.cn/wechat.png)

## License

[MIT License](https://github.com/fantasticmao/mundo/blob/master/LICENSE)

Copyright (c) 2017 fantasticmao
