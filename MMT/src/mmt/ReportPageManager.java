/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mmt;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aditya Mishra
 */
public class ReportPageManager {
    public int curPageIndex = 0;    
    public List pages = new ArrayList();
    
    public static enum PAGETYPE { SINGLE, MULTIPLE}; 
    
    ReportPageManager() {
        curPageIndex = 0;
    }
    
    ReportPageManager(List pages) {
        curPageIndex = 0;
        this.pages = pages;
    }
    
    boolean isEmpty() {
        return getPageCount() == 0;
    }
    
    boolean isNextPageAvailable() {
        if (curPageIndex < pages.size() -1) {
            return true;
        }
        
        return false;
    }
    
    boolean isPrevPageAvailable() {
        if (curPageIndex > 0) {
            return true;
        }
        
        return false;
    }
    
    public ReportPageManager.PAGETYPE getPageType() {
        if ( pages.size() > 1) {
            return ReportPageManager.PAGETYPE.MULTIPLE;
        }
        
        return ReportPageManager.PAGETYPE.SINGLE;
    }
    
    public int getPageCount() {
        return pages.size();
    }
    
    public int getCurPageNumber() {
        return curPageIndex+1;  // because page-index is 0-based
    }
    
    public Object getCurPage() {
        if (curPageIndex >=0 && curPageIndex < pages.size() ) {
            return pages.get(curPageIndex);
        }

        return null;
    }
    
    public Object getFirstPage() {
        curPageIndex = 0;
        return getCurPage();
    }
    
    public Object getLastPage() {
        curPageIndex = pages.size()-1;
        return getCurPage();
    }
    
    public Object moveNextPage() {
        if ( isNextPageAvailable() ) {
            return pages.get(++curPageIndex);
        }
        
        return null;
    }
    
    public Object movePrevPage() {
        if ( isPrevPageAvailable() ) {
            return pages.get(--curPageIndex);
        }

        return null;
    }
}
