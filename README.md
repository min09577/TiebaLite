# <p align="center">Tieba Lite</p>
<p align="center">
    <a href="https://github.com/HuanCheng65/TiebaLite/actions/workflows/build.yml">
        <img alt="Build Status" src="https://github.com/HuanCheng65/TiebaLite/actions/workflows/build.yml/badge.svg?branch=4.0-dev">
    </a>
    <a href="https://t.me/tblite_discuss">
        <img alt="Status" src="https://img.shields.io/badge/-Telegram-blue?logo=telegram&style=flat">
    </a>
</p>

贴吧 Lite 是一个**非官方**的贴吧客户端。

## 说明

**本软件及源码仅供学习交流使用，严禁用于商业用途。**

## 友情链接

+ [Starry-OvO/aiotieba: Asynchronous I/O Client for Baidu Tieba](https://github.com/Starry-OvO/aiotieba)
+ [n0099/tbclient.protobuf: 百度贴吧客户端 Protocol Buffers 定义文件合集](https://github.com/n0099/tbclient.protobuf)

---

## 🤖 AI 迭代升级声明 / AI Iteration Notice / AI 반복 업그레이드 성명 / AIイテレーション更新声明

<details>
<summary>🇨🇳 中文</summary>

本项目原由 **HuanCheng65** 开发并维护，现已归档/停止更新。

本 Fork 由 AI（Hermes Agent）维护，旨在用于学习和延续项目的目的。原作者的所有工作和贡献均被完整保留，我们对原作者的辛勤工作表示由衷的敬意。

这是一个 **AI 辅助的迭代升级版本**，所有原始免责声明仍然适用。

- 原始仓库：[HuanCheng65/TiebaLite](https://github.com/HuanCheng65/TiebaLite)
- Fork 仓库：[min09577/TiebaLite](https://github.com/min09577/TiebaLite)

</details>

<details>
<summary>🇯🇵 日本語</summary>

本プロジェクトは **HuanCheng65** によって開発・メンテナンスされていましたが、現在はアーカイブ/開発終了となっています。

本 Fork は AI（Hermes Agent）によって維持されており、学習とプロジェクトの継続を目的としています。原作者のすべての功績と貢献は完全に保持されており、原作者の努力に深く敬意を表します。

これは **AI アシストによるイテレーション更新版** であり、すべての元の免責事項が引き続き適用されます。

- 元のリポジトリ：[HuanCheng65/TiebaLite](https://github.com/HuanCheng65/TiebaLite)
- Fork リポジトリ：[min09577/TiebaLite](https://github.com/min09577/TiebaLite)

</details>

<details>
<summary>🇰🇷 한국어</summary>

본 프로젝트는 **HuanCheng65**에 의해 개발 및 유지 관리되었으며, 현재 아카이브/개발이 중단되었습니다.

이 Fork는 AI(Hermes Agent)에 의해 유지 관리되며, 학습 및 프로젝트 지속 목적으로 운영됩니다. 원저자의 모든 노력과 기여는 완전히 보존되어 있으며, 원저자의 노고에 깊은 경의를 표합니다.

이것은 **AI 지원 반복 업그레이드 버전**이며, 모든 원래 면책 조항이 계속 적용됩니다.

- 원본 저장소: [HuanCheng65/TiebaLite](https://github.com/HuanCheng65/TiebaLite)
- Fork 저장소: [min09577/TiebaLite](https://github.com/min09577/TiebaLite)

</details>

<details>
<summary>🇺🇸 English</summary>

This project was originally developed and maintained by **HuanCheng65** and has since been archived/discontinued.

This fork is maintained by AI (Hermes Agent) for the purpose of learning and continuation of the project. All original work and credits of the original author are fully preserved, and we express our sincere respect for the original author's efforts.

This is an **AI-assisted iterative upgrade version**, and all original disclaimers still apply.

- Original Repository: [HuanCheng65/TiebaLite](https://github.com/HuanCheng65/TiebaLite)
- Fork Repository: [min09577/TiebaLite](https://github.com/min09577/TiebaLite)

</details>

---

## 📋 版本迭代记录 / Version History

| 版本 / Version | 日期 / Date | 说明 / Description |
|---|---|---|
| v4.0.0-beta.1 | 2024-02-02 | 原始版本发布 / Original release by HuanCheng65 |
| v4.0.0-ai.1 | 2026-06-09 | AI 迭代：文档完善、四国语言声明 / AI Iteration: documentation, 4-language declarations |
| v4.0.0-ai.2 | 2026-06-09 | AI 迭代：安全修复、网络安全配置 / AI Iteration: security fixes, network security config |
| v4.0.0-ai.3 | 2026-06-09 | AI 迭代：CI 修复、构建成功 / AI Iteration: CI fixes, build succeeded ✅ |

---

## 🛠️ 构建说明 / Build Instructions

### 环境要求 / Prerequisites

- **JDK 17+**
- **Android SDK** with **compileSdk 34**
- Android Studio (推荐 / Recommended)

### 签名配置 / Signing Configuration

创建 `keystore.properties` 文件用于 Release 签名配置：

Create a `keystore.properties` file for release signing:

```properties
storeFile=your_keystore_file.jks
storePassword=your_store_password
keyAlias=your_key_alias
keyPassword=your_key_password
```

> ⚠️ **注意 / Note:** 请勿将 `keystore.properties` 文件提交到版本控制系统。/ Do NOT commit the `keystore.properties` file to version control.

### 构建命令 / Build Commands

```bash
# Debug 构建 / Debug Build
./gradlew assembleDebug

# Release 构建 / Release Build
./gradlew assembleRelease
```

构建产物位于 `app/build/outputs/apk/` 目录。

Build outputs are located in the `app/build/outputs/apk/` directory.

---

## ⚠️ 免责声明 / Disclaimer

<details>
<summary>🇨🇳 中文</summary>

1. 本软件为**非官方**贴吧客户端，与百度公司无任何关联。
2. 本软件及源码**仅供学习交流使用，严禁用于商业用途**。
3. 使用本软件所产生的一切后果由使用者自行承担。
4. 本软件不保证功能的完整性和稳定性。
5. AI 辅助迭代版本不承担因使用本软件而产生的任何直接或间接损失。

</details>

<details>
<summary>🇯🇵 日本語</summary>

1. 本ソフトウェアは**非公式**の贴吧クライアントであり、百度社とは一切の関係がありません。
2. 本ソフトウェアおよびソースコードは**学習・交流のみを目的としており、商業利用は厳禁**です。
3. 本ソフトウェアの使用により生じた一切の結果は、使用者自身が責任を負います。
4. 本ソフトウェアは機能の完全性と安定性を保証するものではありません。
5. AI アシストイテレーション版は、本ソフトウェアの使用により生じた直接的または間接的な損失について責任を負いません。

</details>

<details>
<summary>🇰🇷 한국어</summary>

1. 본 소프트웨어는 **비공식**贴吧 클라이언트이며, 바이두와는 아무런 관련이 없습니다.
2. 본 소프트웨어 및 소스코드는 **학습 및 교류 목적으로만 사용되며, 상업적 사용은 엄격히 금지**됩니다.
3. 본 소프트웨어 사용으로 발생한 모든 결과는 사용자가 책임집니다.
4. 본 소프트웨어는 기능의 완전성과 안정성을 보장하지 않습니다.
5. AI 지원 반복 업그레이드 버전은 본 소프트웨어 사용으로 인한 직접적 또는 간접적 손실에 대해 책임지지 않습니다.

</details>

<details>
<summary>🇺🇸 English</summary>

1. This software is an **unofficial** Tieba client and is not affiliated with Baidu, Inc.
2. This software and source code are **for learning and communication purposes only. Commercial use is strictly prohibited**.
3. All consequences arising from the use of this software are borne by the user.
4. This software does not guarantee the completeness and stability of its features.
5. The AI-assisted iterative upgrade version does not assume any responsibility for direct or indirect losses arising from the use of this software.

</details>

---

<p align="center">
    <sub>原作者 / Original Author: <a href="https://github.com/HuanCheng65">HuanCheng65</a> | 许可证 / License: GPL v3</sub>
</p>
