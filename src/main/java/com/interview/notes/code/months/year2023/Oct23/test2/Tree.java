package com.interview.notes.code.months.year2023.Oct23.test2;

import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class Tree {
    private List<Branch> branches;
    private List<Leaf> leaves;
    private Trunk trunk;
    private List<Root> roots;
    private Bark bark;

    public Tree() {
        this.branches = new ArrayList<>();
        this.leaves = new ArrayList<>();
        this.roots = new ArrayList<>();
    }

    public List<Branch> getBranches() {
        return branches;
    }

    public void addBranch(Branch branch) {
        this.branches.add(branch);
    }

    public List<Leaf> getLeaves() {
        return leaves;
    }

    public void addLeaf(Leaf leaf) {
        this.leaves.add(leaf);
    }

    public Trunk getTrunk() {
        return trunk;
    }

    public void setTrunk(Trunk trunk) {
        this.trunk = trunk;
    }

    public List<Root> getRoots() {
        return roots;
    }

    public void addRoot(Root root) {
        this.roots.add(root);
    }

    public Bark getBark() {
        return bark;
    }

    public void setBark(Bark bark) {
        this.bark = bark;
    }
}
