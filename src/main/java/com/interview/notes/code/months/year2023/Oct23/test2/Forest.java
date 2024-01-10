package com.interview.notes.code.months.year2023.Oct23.test2;

import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.List;

public class Forest {
    private List<Tree> trees;

    public Forest() {
        this.trees = new ArrayList<>();
    }

    public List<Tree> getTrees() {
        return trees;
    }

    public void addTree(Tree tree) {
        this.trees.add(tree);
    }
}