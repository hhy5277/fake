# 项目简介

本项目用于手写各种常见工具。

仅供学习之用，不要用在生产。

# 变更日志

> [变更日志](doc/CHANGELOG.md)

# 使用方式

本项目用于生成 mvn archetype.

## 依赖

maven 3.9

## 命令

- 下载

```ch
$ git clone https://github.com/houbb/maven-archetype.git
```

- 安装

进入项目根目录

```sh
$   mvn archetype:create-from-project
$   cd target/generated-sources/archetype/
$   mvn install
```

- 使用

```sh
$ mkdir /tmp/archetype
$ cd /tmp/archetype
$ mvn archetype:generate -DarchetypeCatalog=local
```



