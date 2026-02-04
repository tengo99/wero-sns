# We-Ro-SNS
<br><br>

WE-RO
=============
現代社会を生きる人々のための **感情共有型 SNS** です。<br>
REST API 形式のサーバー構成で、フロントエンドは React、バックエンドは Spring Boot にて実装しました。<br>

## プロジェクト全体構成
![Sitemap Whiteboard in Green Purple Basic Style (2)](https://github.com/Path-Finder-Org/We-Ro-SNS/assets/104974710/3f1777d7-7ab3-4ebb-99bf-31f36b172a0e)

- GitHub Actions を用いた CI/CD を構築  
- すべてのサーバーは Amazon Web Services 上で稼働  
- 稼働中のサーバー：Main We-Ro Server / React Server / MySQL Server  

---

## プロジェクトの主な関心事項

<b>共通方針</b><br>
- 継続的なパフォーマンス改善  
- 可読性・保守性を意識したリファクタリング  

<br>

<b>コード規約</b><br>
- Google Code Style を遵守  
- Notion にコード規約を明文化し、チーム全員で統一ルールを徹底  
- 参考リンク：<https://www.notion.so/cf5968376cfc4478a3768e4d8a3a96d1>  

<br>

<b>パフォーマンス最適化</b><br>
- GitHub Actions を活用したテスト自動化  
- チーム内でコードレビューを行い、互いの実装を共有・理解  

<br>

### パフォーマンステスト
Postman を用いて API の動作および性能テストを実施しています。<br>

---

## 使用技術・開発環境
Spring Boot / Gradle / JPA / React / Docker / MySQL / Jenkins / Java 17 / Amazon Web Services  
<br>

---

## Notion
<https://www.notion.so/ec4700e35f214a2aa95e8ecf51b506ee><br>
プロジェクトで使用した技術、ERD、API 仕様書などをまとめています。<br>

---

## CI
- Jenkins（※ 現在は運用終了）  
- Amazon Web Services を利用  
- Pull Request 作成時に自動 Build / Test を実行  
- 非ログイン状態でも確認可能  

---

## CD
- Docker イメージを作成してデプロイ  
- CI サーバーでのビルド完了後、Shell Script により Docker Hub へイメージを登録  
- Push 完了後、Delfood メインサーバーが Docker Hub からイメージを取得して実行  

---

## Database
- **MySQL**  
  - cafe24 Web Hosting サービスを利用  
- **Redis**  
  - Docker コンテナとして運用  

---

## 画面設計
kakao oven：<https://ovenapp.io/view/SaTiTCEQyNfk5FdeOq1lDkJuGsNogCVE/>

### S/W 画面実例 1
![リドミー - 画面実例 (1)](https://github.com/Path-Finder-Org/We-Ro-SNS/assets/104974710/b0c5b327-4567-44fa-8e0a-47fddf03131a)

### S/W 画面実例 2
![リドミー - 画面実例 2](https://github.com/Path-Finder-Org/We-Ro-SNS/assets/104974710/a6d343da-bbcf-47c0-b386-5a0122641d9a)

---

## プロジェクト DB ERD
（2024-06-03 更新）  
![ERD](https://github.com/Path-Finder-Org/We-Ro-SNS/assets/104974710/47d53569-b0ac-44de-b26f-d2a5804a9a54)
