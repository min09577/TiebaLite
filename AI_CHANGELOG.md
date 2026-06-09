# AI Iteration Notice / AI 迭代升级说明

> 本文档记录 AI 辅助迭代升级的所有变更内容。
> This document records all changes made by AI-assisted iterative upgrades.

---

## v4.0.0-ai.3 (2026-06-09) ✅ CI 构建成功

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

本版本由 AI（Hermes Agent by Nous Research）进行首次迭代升级，主要变更如下：

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

This version is the first iterative upgrade performed by AI (Hermes Agent by Nous Research):

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
