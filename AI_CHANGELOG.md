# AI Iteration Notice / AI 迭代升级说明

> 本文档记录 AI 辅助迭代升级的所有变更内容。
> This document records all changes made by AI-assisted iterative upgrades.

---

## v4.0.0-ai.2 (2026-06-09)

### 🇨🇳 中文

**安全修复与兼容性改进**

#### 安全修复
- 移除 `usesCleartextTraffic="true"`，改用 `network_security_config.xml` 精确控制
- 默认禁止明文 HTTP 流量，仅允许局域网开发环境
- 适配 Android SDK 35 的网络安全策略要求

#### 兼容性改进
- 升级 compileSdk 34 → 35，buildTools 34.0.0 → 35.0.0
- 升级 Compose Compiler 1.5.8 → 1.5.11
- 修复 Gradle wrapper 8.2 → 8.6

### 🇺🇸 English

**Security Fixes & Compatibility Improvements**

#### Security
- Replaced `usesCleartextTraffic="true"` with proper `network_security_config.xml`
- Default deny cleartext HTTP traffic, allow only LAN for development
- Adapted to Android SDK 35 network security requirements

#### Compatibility
- Upgraded compileSdk 34 → 35, buildTools 34.0.0 → 35.0.0
- Upgraded Compose Compiler 1.5.8 → 1.5.11
- Fixed Gradle wrapper 8.2 → 8.6

---

## v4.0.0-ai.1 (2026-06-09)

### 🇨🇳 中文

**AI 迭代升级版本 v4.0.0-ai.1**

本版本由 AI（Hermes Agent by Nous Research）进行首次迭代升级，主要变更如下：

#### 依赖升级
- Kotlin: 1.9.22 → 1.9.24
- KSP: 1.9.22-1.0.17 → 1.9.24-1.0.20
- Compose BOM: 2024.01.00 → 2024.02.00
- AGP: 8.2.2 → 8.3.2
- Gradle: 8.2 → 8.6
- Accompanist: 0.34.0 → 0.34.2
- Hilt: 2.46.1 → 2.50
- Lifecycle: 2.7.0 → 2.7.1
- Activity Compose: 1.8.2 → 1.8.3
- ExoPlayer (Media3): 1.2.1 → 1.2.2
- Navigation Compose: 2.7.6 → 2.7.7
- Core KTX: 1.12.0 → 1.13.0
- OkHttp: 4.12.0 → 4.12.1
- Glide: 4.16.0 → 4.16.1
- Lottie: 6.3.0 → 6.4.0

#### 构建修复
- 创建 `keystore.properties` 签名配置文件
- 确保 `.gitignore` 包含 `keystore.properties`

#### 文档完善
- 更新 README.md：保留原作者全部声明
- 新增四国语言（中/日/韩/英）AI 迭代声明
- 新增构建说明和版本记录
- 新增免责声明

---

### 🇺🇸 English

**AI Iteration Version v4.0.0-ai.1**

This version is the first iterative upgrade performed by AI (Hermes Agent by Nous Research). Key changes:

#### Dependency Upgrades
- Kotlin: 1.9.22 → 1.9.24
- KSP: 1.9.22-1.0.17 → 1.9.24-1.0.20
- Compose BOM: 2024.01.00 → 2024.02.00
- AGP: 8.2.2 → 8.3.2
- Gradle: 8.2 → 8.6
- Accompanist: 0.34.0 → 0.34.2
- Hilt: 2.46.1 → 2.50
- And other minor dependency bumps

#### Build Fixes
- Created `keystore.properties` signing configuration
- Ensured `.gitignore` includes `keystore.properties`

#### Documentation
- Updated README.md with full preservation of original author credits
- Added 4-language AI iteration declarations
- Added build instructions and version history
- Added disclaimers

---

### 🇯🇵 日本語

**AI イテレーション版 v4.0.0-ai.1**

本バージョンはAI（Hermes Agent by Nous Research）による初回イテレーションアップグレードです。主な変更点は以下の通りです。

#### 依存関係のアップグレード
- Kotlin、KSP、Compose BOM、AGP、Gradle 等の主要依存関係を最新安定版に更新

#### ビルド修正
- `keystore.properties` 署名設定ファイルを作成
- `.gitignore` に `keystore.properties` を追加

#### ドキュメント
- README.md を更新、原作者のクレジットを完全に保持
- 4か国語のAIイテレーション宣言を追加

---

### 🇰🇷 한국어

**AI 반복 업그레이드 버전 v4.0.0-ai.1**

이 버전은 AI(Hermes Agent by Nous Research)에 의한 첫 번째 반복 업그레이드입니다.

#### 종속성 업그레이드
- Kotlin, KSP, Compose BOM, AGP, Gradle 등 주요 종속성을 최신 안정 버전으로 업데이트

#### 빌드 수정
- `keystore.properties` 서명 설정 파일 생성
- `.gitignore`에 `keystore.properties` 추가

#### 문서
- README.md 업데이트, 원저자 크레딧 완전 보존
- 4개국어 AI 반복 업그레이드 선언 추가
