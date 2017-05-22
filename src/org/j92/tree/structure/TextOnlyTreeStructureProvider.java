package org.j92.tree.structure;

import com.intellij.ide.projectView.TreeStructureProvider;
import com.intellij.ide.projectView.ViewSettings;
import com.intellij.ide.projectView.impl.nodes.PsiFileNode;
import com.intellij.ide.util.treeView.AbstractTreeNode;
import com.intellij.openapi.fileTypes.PlainTextFileType;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;

public class TextOnlyTreeStructureProvider implements TreeStructureProvider{
    @NotNull
    @Override
    public Collection<AbstractTreeNode> modify(
            @NotNull AbstractTreeNode parent,
            @NotNull Collection<AbstractTreeNode> children,
            ViewSettings viewSettings) {
        ArrayList<AbstractTreeNode> nodes = new ArrayList<>();

        for (AbstractTreeNode child : children) {
            if (child instanceof PsiFileNode) {
                VirtualFile file = ((PsiFileNode) child).getVirtualFile();
                if (file != null && !file.isDirectory() && !(file.getFileType() instanceof PlainTextFileType)) {
                    continue;
                }
            }
            nodes.add(child);
        }

        return nodes;
    }

    @Nullable
    @Override
    public Object getData(Collection<AbstractTreeNode> selected, String dataName) {
        return null;
    }
}
