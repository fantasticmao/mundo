# Mundo

[![Actions Status](https://github.com/fantasticmao/mundo/workflows/ci/badge.svg)](https://github.com/fantasticmao/mundo/actions)
![JDK Version](https://img.shields.io/badge/JDK-21%2B-blue)
[![Maven Central](https://img.shields.io/maven-central/v/cn.fantasticmao.mundo/mundo-all.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22cn.fantasticmao.mundo%22)
[![image](https://img.shields.io/badge/license-MIT-green.svg)](https://github.com/fantasticmao/mundo/blob/master/LICENSE)

> Mundo 命名的灵感来源于英雄联盟中一个经常被挨揍的英雄：祖安狂人——蒙多医生。

Mundo 是一个开箱即用（可能没用）的小型业务开发组件，它为 [Spring Boot](https://spring.io/projects/spring-boot)
应用提供了一些常用业务实现和编程规范约定。它的主要内容来源于我自己在日常学习和工作过程中遇到的一些个人需求，所以你很大可能是不需要它的......

## 特性列表

### mundo-core

- [x] `cn.fantasticmao.mundo.core.util.*` 提供一些常用的工具类

### mundo-data

- [x] `AbstractEntity` 定义数据库 Entity 类中的常用字段
- [x] `@RoutingSeed` 和 `RoutingDataSource` 支持数据源级别的路由策略
- [x] `IdGenerator` 提供分布式 ID 算法的实现

### mundo-web

- [x] `GeneralControllerAdvice` 提供全局的异常处理机制

## 反馈问题

- [GitHub Issues](https://github.com/fantasticmao/mundo/issues/)
- [Telegram](https://t.me/fantasticmao)
- [WeChat](https://fantasticmao.cn/wechat.png)

## 许可证

[MIT License](https://github.com/fantasticmao/mundo/blob/master/LICENSE)

Copyright (c) 2017 fantasticmao
