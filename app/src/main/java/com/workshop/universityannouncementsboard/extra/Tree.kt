package com.workshop.universityannouncementsboard.extra

sealed class Tree {
    override fun toString(): String {
        return treeToString(this, StringBuilder()).toString()
    }
}

class Leaf(val value: String) : Tree()
class Node(val left: Tree, val right: Tree) : Tree()

private fun treeToString(tree: Tree, sb: StringBuilder): StringBuilder {
    when (tree) {
        is Leaf -> sb.append(tree.value)
        is Node -> {
            val node = tree as Node
            sb.append("(")
            treeToString(node.left, sb)
            sb.append(", ")
            treeToString(node.right, sb)
            sb.append(")")
        }
    }
    return sb
}