package tree;

import java.util.List;
import java.util.Collection;

public interface Tree<K extends Comparable<K>, V> {
  public NonEmptyTree<K, V> add(K key, V value);
  public int size();
  public V lookup(K key);
  public int numLeaves();
  public Tree<K, V> subTree(K fromKey, K toKey);
  public List<K> pathToRoot(K key);
  public Collection<K> keyCollection();
  public K max() throws EmptyTreeException;
  public K min() throws EmptyTreeException;
  public Tree<K, V> delete(K key);
}
