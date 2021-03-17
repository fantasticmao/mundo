# Mundo [![Actions Status](https://github.com/fantasticmao/mundo/workflows/action/badge.svg)](https://github.com/fantasticmao/mundo/actions) [![image](https://img.shields.io/badge/license-MIT-green.svg)](https://github.com/fantasticmao/mundo/blob/master/LICENSE)

> The naming of Mundo was inspired by a champion in the League of Legends who was always beaten: the madman of Zaun - Dr. Mundo.

Mundo is a small business component that is out of the box (and maybe useless). It can provide some basic business
implementation and programming conventions for Spring Boot applications. Its main content comes from some special needs
in my study and work, so you probably don't need it.

## Features

### mundo-core

- [x] `@AssertFalse` `@PrintArgs` `@Timeout` support printing log in the annotation-driven mode
- [x] `ListBuilder` `SetBuilder` `MapBuilder` support using the Builder design-pattern to create useful collections
- [x] `com.mundo.core.util.*` support some simple and tiny business util classes
- [x] `StackPointer` support printing the call stack of the current thread

### mundo-data

- [x] `AbstractDomain` specifies basic fields in the Domain object
- [x] `MemcacheLoadingCache` support forwarding the storage layer of Guava Cache to Memcached or Redis
- [x] `PartitionDataSource` `@PartitionParam` support data source routing at the database level
- [x] `Snowflake` implement the Twitter's snowflake algorithm

### mundo-web

- [ ] `@CheckCsrf` `@CheckLogin` support session validation in the annotation-driven mode
- [x] `WeChatConfigController` support signature verification when programming with WeChat open platform
- [x] `JsonApi` specifies JSON basic fields in the HTTP API response

## Feedback

- [GitHub Issues](https://github.com/fantasticmao/mundo/issues/)
- [Telegram](https://t.me/fantasticmao)
- [WeChat](https://blog.fantasticmao.cn/images/weixin.png)

## License

[MIT License](https://github.com/fantasticmao/mundo/blob/master/LICENSE)

Copyright (c) 2017 Mao Mao