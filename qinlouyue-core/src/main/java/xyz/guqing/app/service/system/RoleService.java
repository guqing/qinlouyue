package xyz.guqing.app.service.system;


import xyz.guqing.app.bean.entity.system.Relation;
import xyz.guqing.app.bean.entity.system.Role;
import xyz.guqing.app.bean.vo.node.Node;
import xyz.guqing.app.bean.vo.node.ZTreeNode;
import xyz.guqing.app.dao.system.RelationRepository;
import xyz.guqing.app.dao.system.RoleRepository;
import xyz.guqing.app.service.BaseService;
import xyz.guqing.app.utils.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created  on 2018/3/25 0025.
 *
 * @author enilu
 */
@Service
public class RoleService extends BaseService<Role,Long,RoleRepository> {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RelationRepository relationRepository;

    public List<ZTreeNode> roleTreeList() {
        List list = roleRepository.roleTreeList();
        List<ZTreeNode> treeNodes = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Object[] arr = (Object[]) list.get(i);
            ZTreeNode node = new ZTreeNode();
            node.setId(Long.valueOf(arr[0].toString()));
            node.setpId(Long.valueOf(arr[1].toString()));
            node.setName(arr[2].toString());
            node.setOpen(Boolean.valueOf(arr[3].toString()));
            treeNodes.add(node);
        }
        return treeNodes;
    }


    public List<ZTreeNode> roleTreeListByRoleId(Long[] ids) {
        List list = roleRepository.roleTreeListByRoleId(ids);
        List<ZTreeNode> treeNodes = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Object[] arr = (Object[]) list.get(i);
            ZTreeNode node = new ZTreeNode();
            node.setId(Long.valueOf(arr[0].toString()));
            node.setpId(Long.valueOf(arr[1].toString()));
            node.setName(arr[2].toString());
            node.setOpen(Boolean.valueOf(arr[3].toString()));
            node.setChecked(Boolean.valueOf(arr[4].toString()));
            treeNodes.add(node);
        }
        return treeNodes;
    }


    public void setAuthority(Long roleId, String ids) {
        // 删除该角色所有的权限
        relationRepository.deleteByRoleId(roleId);

        // 添加新的权限
        for (Long id : Convert.toLongArray(true, Convert.toStrArray(",", ids))) {
            Relation relation = new Relation();
            relation.setRoleid(roleId);
            relation.setMenuid(id);
            relationRepository.save(relation);
        }
    }

    public void delRoleById(Long roleId) {
        //删除角色
        roleRepository.deleteById(roleId);

        // 删除该角色所有的权限
        relationRepository.deleteByRoleId(roleId);
    }


    public List<Node> generateRoleTree(List<ZTreeNode> list) {
        List<Node> nodes = new ArrayList<>();
        for (ZTreeNode role : list) {
            Node roleNode = new Node();
            roleNode.setId(role.getId());
            roleNode.setName(role.getName());
            roleNode.setPid(role.getpId());
            roleNode.setChecked(role.getChecked());
            nodes.add(roleNode);
        }
        return nodes;
    }
    @Override
    public Role get(Long id) {
        Optional<Role> optional = roleRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    public List<Role> findByName(String name) {
        return roleRepository.findByName(name);
    }
}
