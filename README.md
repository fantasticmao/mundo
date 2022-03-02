# Mundo

[![Actions Status](https://github.com/fantasticmao/mundo/workflows/ci/badge.svg)](https://github.com/fantasticmao/mundo/actions)
![JDK Version](https://img.shields.io/badge/JDK-11%2B-blue)
[![Maven Central](https://img.shields.io/maven-central/v/cn.fantasticmao.mundo/mundo-all.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22cn.fantasticmao.mundo%22)
[![image](https://img.shields.io/badge/license-MIT-green.svg)](https://github.com/fantasticmao/mundo/blob/master/LICENSE)

> The naming of Mundo was inspired by a champion in the League of Legends who was always beaten: the madman of Zaun - Dr. Mundo.

Mundo is a tiny and out-of-the-box (also maybe useless) component for business programming. It provides some basic
business implementation and programming conventions for [Spring Boot](https://spring.io/projects/spring-boot)
applications. Its main content comes from some personal requirements that I meet in my daily study and work, so you
probably don't need it.

## Features

### mundo-core

- [x] `@AssertFalse` `@PrintArgs` `@Timeout` support logging with the annotation-driven mode
- [x] `cn.fantasticmao.mundo.core.util.*` implement some simple business util classes

### mundo-data

- [x] `AbstractDomain` specifies basic fields in the Domain object
- [x] `MemcacheLoadingCache` support forwarding the storage layer of Guava Cache to Memcached or Redis
- [x] `PartitionDataSource` `@PartitionParam` support data source routing at the database level
- [x] `Snowflake` implement the Twitter's snowflake algorithm

### mundo-web

- [ ] `@CheckCsrf` `@CheckLogin` support session validation with the annotation-driven mode
- [x] `JsonApi` specifies JSON basic fields in the RESTFul API response
- [x] `WeChatServerConfig` support signature verification when programming with WeChat open platform

## Feedback

- [GitHub Issues](https://github.com/fantasticmao/mundo/issues/)
- [Telegram](https://t.me/fantasticmao)
- [WeChat](https://fantasticmao.cn/wechat.png)

## License

[MIT License](https://github.com/fantasticmao/mundo/blob/master/LICENSE)

Copyright (c) 2017 fantasticmao