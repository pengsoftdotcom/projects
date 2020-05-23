import { NzTreeNodeOptions } from 'ng-zorro-antd';

export class EntityUtils {

    private static excludes = ['createdAt', 'updatedAt'];

    static toJSON(bean: any): string {
        if (bean) {
            this.excludes.forEach(name => {
                delete bean[name];
            });
            for (const name in bean) {
                if (bean.hasOwnProperty(name)) {
                    if (bean[name] === null || bean[name] === undefined || bean[name] === '') {
                        delete bean[name];
                    }
                }
            }
        }
        return encodeURIComponent(JSON.stringify(bean));
    }

    static fromJSON(json: string): any {
        if (json) {
            return JSON.parse(decodeURIComponent(json));
        } else {
            return null;
        }
    }

    static convertTreeBeanToTreeNode(bean: any): NzTreeNodeOptions {
        return {
            title: bean.name,
            value: bean,
            key: bean.id,
            isLeaf: bean.leaf,
            children: bean.children
        };
    }

    static convertTreeNodeToTreeBean(node: NzTreeNodeOptions): any {
        const value = node.value;
        value.children = node.children;
        return value;
    }

    static convertTreeToList(tree: Array<NzTreeNodeOptions>, convert?: (node: NzTreeNodeOptions) => any): Array<any> {
        convert = convert ? convert : this.convertTreeNodeToTreeBean;
        const list = [];
        const queue = [];
        if (tree) {
            tree.forEach(data => {
                queue.push(data);
                while (queue.length > 0) {
                    let parent = queue.pop();
                    parent = convert(parent);
                    list.push(parent);
                    if (parent.children) {
                        parent.children.reverse().forEach(child => queue.push(child));
                    }
                }
            });
        }
        return list;
    }

    static convertListToTree(list: Array<any>, convert?: (bean: any) => NzTreeNodeOptions): Array<NzTreeNodeOptions> {
        convert = convert ? convert : this.convertTreeBeanToTreeNode;
        const roots = [];
        const queue = [];
        let nodes = list.map(bean => convert(bean));
        nodes.filter(source => !source.value.parent || nodes.every(target => source.value.parent.id !== target.value.id))
            .forEach(node => {
                queue.push(node);
                roots.push(node);
            });
        nodes = nodes.filter(source => !roots.some(target => source.value.id === target.value.id));
        roots.sort(this.sort);
        while (queue.length > 0) {
            const node = queue.shift();
            const parentIds = node.value.parentIds ? node.value.parentIds + '::' + node.value.id : node.value.id;
            node.children = nodes.filter(child => child.value.parentIds === parentIds).sort(this.sort);
            nodes = nodes.filter(source => !node.children.some(target => source.value.id === target.value.id));
            node.children.forEach(child => queue.push(child));
        }
        return roots;
    }

    static sort(node1: NzTreeNodeOptions, node2: NzTreeNodeOptions): number {
        if (node1.value.code !== undefined && node2.value.code !== undefined) {
            if ([node1.value.code, node2.value.code].sort()[0] === node1.value.code) {
                return -1;
            } else {
                return 1;
            }
        }

        if (node1.value.sequence !== undefined && node2.value.sequence !== undefined) {
            if (node1.value.sequence > node2.value.sequence) {
                return 1;
            } else if (node1.value.sequence === node2.value.sequence) {
                return 0;
            } else {
                return -1;
            }
        }

        return 0;
    }

    static filterTree(tree: Array<NzTreeNodeOptions>, options: { filter: (node) => boolean }): Array<any> {
        if (tree) {
            tree = tree.filter(options.filter);
            const queue = [];
            tree.forEach(node => {
                queue.push(node);
                while (queue.length > 0) {
                    const parent = queue.shift();
                    if (parent.children) {
                        parent.children = parent.children.filter(options.filter);
                        parent.children.forEach(child => queue.push(child));
                    }
                }
            });
        }
        return tree;
    }

    static findTreeNodes(tree: Array<NzTreeNodeOptions>, ids: Array<string>): Array<NzTreeNodeOptions> {
        const queue = [];
        const matched = [];
        tree.forEach(node => queue.push(node));
        while (queue.length > 0) {
            const parent = queue.pop();
            if (ids.some(id => id === parent.key)) {
                matched.push(parent);
            }
            if (parent.children) {
                parent.children.forEach(child => queue.push(child));
            }
        }
        return matched;
    }

    static getBeanId(bean: any): string {
        if (typeof bean === 'object' && bean.hasOwnProperty('id')) {
            return bean.id;
        }
        if (typeof bean === 'string') {
            return bean;
        }
        return null;
    }

    static getBeanIds(beans: any): Array<string> {
        if (typeof beans === 'object' && beans.hasOwnProperty('id')) {
            return [beans.id];
        }
        if (typeof beans === 'string') {
            return [beans];
        }
        if (beans instanceof Array && beans.length > 0) {
            if (typeof beans[0] === 'object' && beans.hasOwnProperty('id')) {
                return beans.map(domain => domain.id);
            }
            if (typeof beans[0] === 'string') {
                return beans;
            }
        }
        return [];
    }

    static equals(bean1: any, bean2: any): boolean {
        if (!bean1 && !bean2) {
            return true;
        }

        if (bean1 === bean2) {
            return true;
        }

        if (bean1 && bean2 && bean1.id && bean2.id) {
            return bean1.id === bean2.id;
        }

        return false;
    }

}
