# 🔧 TiebaLite 日报 — 2026-06-12/13

## 阶段 2: compose-destinations 移除 ✅
- **60+ 文件迁移**：@Destination 注解 → 字符串路由
- **DestinationsNavHost** → 手动 NavHost（30 composable + 6 deepLinks）
- 新建 Routes.kt（30 条路由）、ThreadInfoNavType.kt、NavigatorProvider.kt
- 删除 NavSerializer.kt，重写 MyBackHandler
- 移除依赖：compose-destinations-core / compose-destinations-ksp
- 添加依赖：accompanist-navigation-material

## 阶段 3: 全面依赖升级 ✅
| 依赖 | 旧版本 | 新版本 |
|------|--------|--------|
| Kotlin | 1.9.22 | 2.0.21 |
| KSP | 1.9.22-1.0.17 | 2.0.21-1.0.28 |
| Compose BOM | 2024.01.00 | 2024.12.01 |
| Compose Compiler | standalone 1.5.8 | Kotlin 2.0 内置插件 |
| Hilt | 2.46.1 | 2.51.1 |
| Lifecycle | 2.7.0 | 2.8.7 |
| Activity | 1.8.2 | 1.9.3 |
| Core KTX | 1.12.0 | 1.13.1 |
| Navigation Compose | 2.7.6 | 2.8.5 |
| Media3 | 1.2.1 | 1.4.1 |
| Accompanist | 0.34.0 | 0.36.0 |

## API 适配修复
- rememberRipple() → ripple()
- onNewIntent(Intent?) → onNewIntent(Intent)
- SnapFlingBehavior → TargetedFlingBehavior
- beyondBoundsPageCount → beyondViewportPageCount

## 构建结果
- ✅ CI 构建通过
- ✅ v4.0.0-ai.5 tag 已推送
- ✅ 四国语 changelog（中/日/韩/英）
- Debug APK + Release APK 正常产出

## 当前状态
路线 B 100% 完成：compose-destinations 已完全移除，所有依赖升级到最新稳定版，项目可正常构建出 APK。
