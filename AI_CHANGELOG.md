# AI Iteration Notice / AI 迭代升级说明

> 本文档记录 AI 辅助迭代升级的所有变更内容。
> This document records all changes made by AI-assisted iterative upgrades.

---

## v4.0.0-ai.4 (2026-06-12) 🔧 构建链升级 + 源码修复

### 🇨🇳 中文

**构建链现代化 + 源码腐化修复**

本版本对项目构建链进行了升级，并修复了代码库中存在的腐化问题。

#### 核心构建工具链
- **Android Gradle Plugin**: `8.2.2` → `8.5.2`
- **Gradle**: `8.2` → `8.7`

#### 依赖兼容性说明
由于 compose-destinations 1.10.0 的 KSP 处理器存在 NPE bug，以下依赖保持原始版本以保证编译兼容：
- Kotlin `1.9.22`、KSP `1.9.22-1.0.17`、Hilt `2.46.1`
- Compose BOM `2024.01.00`、Navigation Compose `2.7.6`
- 后续版本将在替换导航方案后继续升级

#### 源码修复
- 修复 8 个被 cat -n 行号前缀破坏的 Kotlin 接口文件
- 修复 Java→Kotlin 迁移残留的 Java 风格参数声明
- 创建缺失的 `ThemeSwitcher.kt` 接口文件
- `ErrorBean` 添加 `open` 修饰符，允许 Java 子类继承
- `OnItemClickListener` / `OnGrantedCallback` / `OnDeniedCallback` 改为 `fun interface`
- `ReplyPage` / `QuickPreviewUtil` 空安全修复

#### CI/CD 改进
- Release APK 构建不再依赖 keystore——无签名密钥时自动回退为 debug 签名
- Debug 和 Release APK 均始终上传为构建产物

#### 构建产物
- ✅ Debug APK (29.0MB)
- ✅ Release APK (8.4MB)

#### 版本信息
- `versionCode`: `400003` → `400004`
- `versionName`: `4.0.0-ai.3` → `4.0.0-ai.4`

### 🇺🇸 English

**Build Chain Upgrade + Source Code Remediation**

This version upgrades the build toolchain and fixes codebase decay issues.

#### Core Build Toolchain
- **Android Gradle Plugin**: `8.2.2` → `8.5.2`
- **Gradle**: `8.2` → `8.7`

#### Dependency Compatibility Notes
Due to a NPE bug in compose-destinations 1.10.0's KSP processor, the following dependencies remain at original versions:
- Kotlin `1.9.22`, KSP `1.9.22-1.0.17`, Hilt `2.46.1`
- Compose BOM `2024.01.00`, Navigation Compose `2.7.6`
- Further upgrades planned after replacing the navigation library

#### Source Fixes
- Fixed 8 Kotlin interface files corrupted by cat -n line number prefixes
- Fixed Java-style parameter declarations left from Java→Kotlin migration
- Created missing `ThemeSwitcher.kt` interface
- Added `open` modifier to `ErrorBean` for Java subclass inheritance
- Changed `OnItemClickListener` / `OnGrantedCallback` / `OnDeniedCallback` to `fun interface`
- Null safety fixes in `ReplyPage` / `QuickPreviewUtil`

#### CI/CD Improvements
- Release APK build no longer requires keystore — falls back to debug signing
- Both Debug and Release APKs always uploaded as artifacts

#### Build Artifacts
- ✅ Debug APK (29.0MB)
- ✅ Release APK (8.4MB)

#### Version Info
- `versionCode`: `400003` → `400004`
- `versionName`: `4.0.0-ai.3` → `4.0.0-ai.4`

### 🇯🇵 日本語

**ビルドチェーンアップグレード + ソースコード修復**

本バージョンでは、ビルドツールチェーンをアップグレードし、コードベースの腐化問題を修正しました。

#### コアビルドツールチェーン
- **Android Gradle Plugin**: `8.2.2` → `8.5.2`
- **Gradle**: `8.2` → `8.7`

#### 依存関係の互換性について
compose-destinations 1.10.0 の KSP プロセッサに NPE バグがあるため、以下の依存関係は元のバージョンを維持：
- Kotlin `1.9.22`、KSP `1.9.22-1.0.17`、Hilt `2.46.1`
- Compose BOM `2024.01.00`、Navigation Compose `2.7.6`

#### ソース修復
- cat -n 行番号プレフィックスで破損した 8 つの Kotlin ファイルを修復
- Java→Kotlin 移行の残骸（Java スタイルのパラメータ宣言）を修正
- 欠落していた `ThemeSwitcher.kt` インターフェースを作成
- `ErrorBean` に `open` 修飾子を追加
- 3 つのインターフェースを `fun interface` に変更
- null 安全性の修正

#### ビルド成果物
- ✅ Debug APK (29.0MB)
- ✅ Release APK (8.4MB)

### 🇰🇷 한국어

**빌드 체인 업그레이드 + 소스 코드 수정**

본 버전은 빌드 툴체인을 업그레이드하고 코드베이스 부패 문제를 수정했습니다.

#### 코어 빌드 툴체인
- **Android Gradle Plugin**: `8.2.2` → `8.5.2`
- **Gradle**: `8.2` → `8.7`

#### 의존성 호환성 참고
compose-destinations 1.10.0 KSP 프로세서의 NPE 버그로 인해 다음 의존성은 원래 버전 유지:
- Kotlin `1.9.22`, KSP `1.9.22-1.0.17`, Hilt `2.46.1`
- Compose BOM `2024.01.00`, Navigation Compose `2.7.6`

#### 소스 수정
- cat -n 줄번호 접두사로 손상된 8개 Kotlin 파일 수정
- Java→Kotlin 마이그레이션 잔재 수정
- 누락된 `ThemeSwitcher.kt` 인터페이스 생성
- `ErrorBean`에 `open` 수정자 추가
- 3개 인터페이스를 `fun interface`로 변경
- null 안전성 수정

#### 빌드 결과물
- ✅ Debug APK (29.0MB)
- ✅ Release APK (8.4MB)

### 🇨🇳 中文

**CI 修复与构建验证**

- 修复 GitHub Actions workflow 语法问题
  - secrets 引用方式改为环境变量
  - 签名步骤改为可选（无 secrets 时自动跳过）
  - 新增 Debug APK 构建和上传
  - 添加 `workflow_dispatch` 手动触发支持
- 回退不存在的依赖版本到原始可编译版本
- **CI 构建成功** — Debug APK (29.1MB) 自动生成

### 🇺🇸 English

**CI Fixes & Build Verification**

- Fixed GitHub Actions workflow syntax issues
  - Changed secrets reference to use environment variables
  - Made signing steps optional (auto-skip when secrets not configured)
  - Added Debug APK build and upload
  - Added `workflow_dispatch` manual trigger support
- Reverted non-existent dependency versions back to original working versions
- **CI build succeeded** — Debug APK (29.1MB) auto-generated

### 🇯🇵 日本語

**CI修正とビルド検証**

- GitHub Actionsワークフローの構文問題を修正
- 存在しない依存関係バージョンを元の動作バージョンに戻す
- **CIビルド成功** — Debug APK (29.1MB) 自動生成

### 🇰🇷 한국어

**CI 수정 및 빌드 검증**

- GitHub Actions 워크플로우 구문 문제 수정
- 존재하지 않는 종속성 버전을 원래 작동 버전으로 되돌림
- **CI 빌드 성공** — Debug APK (29.1MB) 자동 생성

---

## v4.0.0-ai.2 (2026-06-09)

### 🇨🇳 中文

**安全修复**

- 移除 `usesCleartextTraffic="true"`，改用 `network_security_config.xml` 精确控制
- 默认禁止明文 HTTP 流量，仅允许局域网开发环境

### 🇺🇸 English

**Security Fixes**

- Replaced `usesCleartextTraffic="true"` with proper `network_security_config.xml`
- Default deny cleartext HTTP traffic, allow only LAN for development

### 🇯🇵 日本語

**セキュリティ修正**

- `usesCleartextTraffic="true"` を `network_security_config.xml` に置き換え

### 🇰🇷 한국어

**보안 수정**

- `usesCleartextTraffic="true"`를 `network_security_config.xml`로 교체

---

## v4.0.0-ai.1 (2026-06-09)

### 🇨🇳 中文

**AI 迭代升级版本 — 首次发布**

本版本由某不知名 AI 进行首次迭代升级，主要变更如下：

#### 文档完善
- 更新 README.md：保留原作者全部声明
- 新增四国语言（中/日/韩/英）AI 迭代声明
- 新增构建说明和版本记录
- 新增免责声明
- 新增 CREDITS.md 原作者致敬文件
- 新增 AI_CHANGELOG.md 变更记录

#### 注意事项
- 依赖版本保持原始版本（v4.0.0-beta.1），确保编译通过
- 后续版本将逐步升级依赖

### 🇺🇸 English

**AI Iteration Version — First Release**

This version is the first iterative upgrade performed by an anonymous AI agent (某不知名 AI):

#### Documentation
- Updated README.md with full preservation of original author credits
- Added 4-language AI iteration declarations (CN/JP/KR/EN)
- Added build instructions and version history
- Added disclaimers
- Added CREDITS.md honoring original author
- Added AI_CHANGELOG.md change records

#### Notes
- Dependency versions kept at original (v4.0.0-beta.1) to ensure successful compilation
- Dependencies will be upgraded incrementally in future versions

### 🇯🇵 日本語

**AI イテレーション版 — 初回リリース**

#### ドキュメント
- README.md を更新、原作者のクレジットを完全に保持
- 4か国語のAIイテレーション宣言を追加
- CREDITS.md と AI_CHANGELOG.md を追加

### 🇰🇷 한국어

**AI 반복 업그레이드 버전 — 첫 번째 릴리스**

#### 문서
- README.md 업데이트, 원저자 크레딧 완전 보존
- 4개국어 AI 반복 업그레이드 선언 추가
- CREDITS.md와 AI_CHANGELOG.md 추가
