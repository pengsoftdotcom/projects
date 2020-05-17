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
    return Object.assign({
      title: bean.name,
      value: bean,
      key: bean.id,
      isLeaf: bean.leaf,
      children: bean.children
    });
  }

  static convertTreeNodeToTreeBean(node: NzTreeNodeOptions): any {
    const value = node.value;
    value.children = node.children;
    return value;
  }

  static convertTreeToList({ tree, convert }: { tree: NzTreeNodeOptions[]; convert?: (node: NzTreeNodeOptions) => any; }): any[] {
    const list = [];
    const stack = [];
    if (tree) {
      tree.forEach(data => {
        stack.push(data);
        while (stack.length > 0) {
          let parent = stack.pop();
          parent = convert ? convert(parent) : this.convertTreeNodeToTreeBean(parent);
          list.push(parent);
          if (parent.children) {
            parent.children.reverse().forEach(child => stack.push(child));
          }
        }
      });
    }
    return list;
  }

  static convertListToTree({ list, convert, sort = (e1, e2) => {
    if (e1.sequence === e2.sequence) {
      return 0;
    } else if (e1.sequence > e2.sequence) {
      return 1;
    } else {
      return -1;
    }
  } }: { list: any[]; convert?: (bean: any) => NzTreeNodeOptions; sort?: (e1, e2) => -1 | 0 | 1; }): NzTreeNodeOptions[] {
    const roots = [];
    const queue = [];
    if (list) {
      let nodes = list.map(bean => convert ? convert(bean) : this.convertTreeBeanToTreeNode(bean));
      nodes.filter(source => !source.value.parent || nodes.every(target => source.value.parent.id !== target.value.id))
        .forEach(node => {
          queue.push(node);
          roots.push(node);
        });
      roots.sort(sort);
      nodes = nodes.filter(source => !roots.some(target => source.value.id === target.value.id));
      while (queue.length > 0) {
        const parent = queue.shift();
        parent.children = nodes.filter(child => child.value.fullId === parent.value.fullId + '::' + child.value.id).sort(sort);
        nodes = nodes.filter(source => !parent.children.some(target => source.value.id === target.value.id));
        parent.children.forEach(child => queue.push(child));
      }
    }
    return roots;
  }

  static filterTree(tree: NzTreeNodeOptions[], options: { filter: (node) => boolean }): any[] {
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

  static findTreeNodes(tree: NzTreeNodeOptions[], ...ids: string[]): NzTreeNodeOptions[] {
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

  static getBeanIds(beans: any): string[] {
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
