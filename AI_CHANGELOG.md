# AI Iteration Notice / AI 迭代升级说明

> 本文档记录 AI 辅助迭代升级的所有变更内容。
> This document records all changes made by AI-assisted iterative upgrades.

---

## v4.0.0-ai.39 (2026-08-31) 🛡️ 特殊字符路由加固

### 🇨🇳 中文

**含链接内容复制 / 外链跳转闪退根治**

#### 问题现象（#19）
复制包含链接的回帖内容、或点击帖子内的网页链接（如网盘链接）时，
应用在导航瞬间闪退；纯文本内容的复制则一切正常。

#### 根因
链接与特殊字符（`/` `?` `&` `:` 等）未经 URI 编码即被拼入导航路由，
破坏了 Navigation 的路由匹配规则，触发 `IllegalArgumentException`。

#### 修复内容
- 复制对话框路由（`copy_dialog`）与网页路由（`webview`）的参数统一采用
  `android.net.Uri.encode` 编码后传递，共加固 **5 处调用点**（楼层复制 ×2、
  楼中楼复制 ×2、外链跳转 ×1），并同步覆盖视频卡片跳转等同族路径
- 读取侧依赖 Navigation 框架的自动解码，往返无损——中文、空格、emoji、
  全角符号、完整 URL 均原样还原
- 排查期间另发现一处字符串插值笔误（分享链接场景），已记录待后续版本处理

#### 验证
- ✅ 复制纯文本 / 含特殊字符内容 → 对话框原文无损展示
- ✅ 点击网盘类外链 → 内置 WebView 完整渲染
- ✅ 视频卡片 → 内嵌播放器正常播放
- ✅ 帖子详情、楼中楼全量回归通过

### 🇺🇸 English

**Crash fix for copying content with links / opening external URLs**

#### Symptom (#19)
Copying a reply containing links, or tapping an in-post URL (e.g. a netdisk link),
crashed the app at navigation time; plain-text copying worked fine.

#### Root cause
Link and special characters (`/` `?` `&` `:` etc.) were concatenated into navigation
routes without URI encoding, breaking Navigation's route matching and throwing
`IllegalArgumentException`.

#### Fix
- All `copy_dialog` and `webview` route parameters are now passed through
  `android.net.Uri.encode` — **5 call sites** hardened (thread copy ×2,
  sub-post copy ×2, external link ×1), plus sibling paths such as video-card jumps
- The reading side relies on Navigation's built-in auto-decoding; round-trips are
  lossless for Chinese text, spaces, emoji, full-width symbols and full URLs

---

## v4.0.0-ai.38 (2026-08-30) 🚀 启动稳定性修复 + 收藏偏好贯通

### 🇨🇳 中文

**启动崩溃根治 + 个体贡献合入 + 收藏页浏览偏好全链路生效**

#### 启动导航作用域修复（合入社区贡献 #15，感谢 @wufeng5702）
- `MainActivityV2` 顶层 `CompositionLocalProvider` 全局提供 `LocalNavigator`，
  根治部分机型冷启动即触发 `IllegalStateException: No navigator is available` 的闪退（#6 / #9 / #17）
- `NotificationsPage` 改为显式注入 navigator，调用链清晰无歧义
- `HotPage` 移除遮蔽函数参数的冗余局部变量，消除导航上下文歧义

#### Java 21 API 前向兼容
- `Extensions.kt` / `PbContentRender.kt` 中 `removeFirst()` / `removeLast()`
  统一替换为 `removeAt()`，避免在高版本 JDK 编译产物上于旧版 Android 运行时触发 `NoSuchMethodError`

#### 收藏页浏览偏好贯通（#3）
- 打通 `from=FROM_STORE` 传递链：`Routes.THREAD` 路由新增 `from` 参数 →
  `MainActivityV2` 解析注入 → 收藏页跳转携带标识
- `ThreadPage` 初始化时读取「从收藏进入默认只看楼主 / 默认倒序浏览」设置并作为有效初始值，
  两个此前「定义而未生效」的开关自此真正生效
- 刷新 / 重试 / 续读路径统一取用运行时状态值，手动切换的浏览偏好在会话内稳定保持

#### 构建产物
- ✅ Debug + Release APK（CI 全量构建通过）

### 🇺🇸 English

**Startup crash fix + community contribution merged + collection browse preferences fully wired**

#### Startup navigation scope fix (community PR #15 by @wufeng5702 merged with thanks)
- `LocalNavigator` is now provided globally at the top-level `CompositionLocalProvider` in `MainActivityV2`,
  eliminating the `IllegalStateException: No navigator is available` cold-start crash on affected devices (#6 / #9 / #17)
- `NotificationsPage` now receives the navigator explicitly; `HotPage` drops the parameter-shadowing local

#### Java 21 API forward compatibility
- `removeFirst()` / `removeLast()` replaced with `removeAt()` in `Extensions.kt` / `PbContentRender.kt`

#### Collection browse preferences wired end-to-end (#3)
- The `from=FROM_STORE` chain now flows through `Routes.THREAD` → `MainActivityV2` → `ThreadStorePage`
- `ThreadPage` reads the "default see-LZ / default descending" preferences as effective initial values;
  refresh / retry / load-more paths consistently use runtime state so manual toggles persist

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
