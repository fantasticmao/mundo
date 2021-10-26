# Mundo

[![Actions Status](https://github.com/fantasticmao/mundo/workflows/ci/badge.svg)](https://github.com/fantasticmao/mundo/actions)
[![Maven Central](https://img.shields.io/maven-central/v/cn.fantasticmao.mundo/mundo-all.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22cn.fantasticmao.mundo%22)
[![image](https://img.shields.io/badge/license-MIT-green.svg)](https://github.com/fantasticmao/mundo/blob/master/LICENSE)

> Mundo 命名的灵感来源于英雄联盟中一个经常被挨揍的英雄：祖安狂人——蒙多医生。

Mundo 是一个开箱即用（可能没用）的小型业务开发组件，它为 [Spring Boot](https://spring.io/projects/spring-boot)
应用提供了一些基础业务实现和编程规范约定。它的主要内容来源于我自己在日常学习和工作过程中遇到的一些个人需求，所以你很大可能是不需要它的......

## 特性列表

### mundo-core

- [x] `@AssertFalse` `@PrintArgs` `@Timeout` 支持以注解驱动方式的日志打印
- [x] `ListBuilder` `SetBuilder` `MapBuilder` 支持使用 Builder 模式来创建常用集合类
- [x] `cn.fantasticmao.mundo.core.util.*` 实现一些简单的业务工具类
- [x] `StackPointer` 打印当前线程的调用栈

### mundo-data

- [x] `AbstractDomain` 约定 Domain 对象中的基本字段
- [x] `MemcacheLoadingCache` 支持将 Guava Cache 的存储层转发至 Memcached 或者 Redis
- [x] `PartitionDataSource` `@PartitionParam` 支持按库级别的数据源路由
- [x] `Snowflake` 实现 Twitter 的雪花算法

### mundo-web

- [ ] `@CheckCsrf` `@CheckLogin` 支持注解驱动方式的 session 校验
- [x] `JsonApi` 约定 RESTful API 响应中的 JSON 基本字段
- [x] `WeChatServerConfig` 支持对接微信开放平台时的 signature 验证

## 反馈问题

- [GitHub Issues](https://github.com/fantasticmao/mundo/issues/)
- [Telegram](https://t.me/fantasticmao)
- [WeChat](https://fantasticmao.cn/wechat.png)

## 许可证

[MIT License](https://github.com/fantasticmao/mundo/blob/master/LICENSE)

Copyright (c) 2017 fantasticmao