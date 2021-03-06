/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.main;

import view.searchResultList.SearchResultListPanel;
import javax.swing.event.*;
import model.SearchResultInfo;

/**
 *
 * @author ThinkPad, Pingchuan.Huang
 */
public class SearchController implements DocumentListener {

    private SearchResultListPanel searchResultListPanel;
    private String inputKeyWord;

    public SearchController(SearchResultListPanel searchResultListPanel) {
        this.searchResultListPanel = searchResultListPanel;
    }

    private void response() {

        this.inputKeyWord = searchResultListPanel.getKeywordField().getText();

        if ("".equals(inputKeyWord)) {
            searchResultListPanel.backToMain();
            searchResultListPanel.getSearchResultListFrame().dispose();
        } else {
            searchResultListPanel.getSearchRsList().setListData(new SearchResultInfo(inputKeyWord).getSearchResultListInfo());
        }

    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        response();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        response();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
    }
}
