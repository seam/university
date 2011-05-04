package org.jboss.seam.university.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * 
 * @author Shane Bryzak
 *
 */
@Entity
public class KeywordMatrix implements Serializable {
    private static final long serialVersionUID = 1945057821464321L;
    
    @Id @GeneratedValue private Long id;
    @ManyToOne private Keyword keywordFrom;
    @ManyToOne private Keyword keywordTo;
    private int score;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public void setKeywordFrom(Keyword keywordFrom) {
        this.keywordFrom = keywordFrom;
    }

    public Keyword getKeywordFrom() {
        return keywordFrom;
    }

    public void setKeywordTo(Keyword keywordTo) {
        this.keywordTo = keywordTo;
    }

    public Keyword getKeywordTo() {
        return keywordTo;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }
}
