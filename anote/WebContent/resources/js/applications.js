/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function confirmMsg() {
    return confirm('ナレッジを削除しますか？');
}
function ajaxError() {
    alert('サーバとの通信でエラーが発生しました。');
}
//Ajaxにエラーがあった場合に呼び出す
jsf.ajax.addOnError(ajaxError);