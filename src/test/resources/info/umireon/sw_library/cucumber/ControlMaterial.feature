# language: ja
フィーチャ: 資料を貸出する
  シナリオ: 書籍が利用可能な場合
    前提 資料コントロールが初期化されている
    かつ 書籍 book1 がある
    もし user1 が book1 を貸出する
    ならば book1 は user1 に貸出中となる

  シナリオ: 書籍が貸出中な場合
    前提 資料コントロールが初期化されている
    かつ 書籍 book1 がある
    もし user1 が book1 を貸出する
    ならば user2 は book1 を利用不可能である

  シナリオ: 書籍が他の利用者に予約されている場合
    前提 資料コントロールが初期化されている
    かつ 書籍 book1 がある
    もし book1 が user1 に予約されている
    ならば user2 は book1 を予約済みであるために貸出できない

  シナリオ: 書籍が未登録な場合
    前提 資料コントロールが初期化されている
    ならば user1 は book3 を存在しないために貸出できない