# Fog Search Tree

## [see other projects](https://github.com/michelesanfilippo)



### What is a Fog Search Tree ?

It's a structure based on Binary Search Tree. 

Every node has to be insert following BST structure. If the root is null, the node just inserted will be our root, else we have to check if the new node is greater or less than root so we know if we have to move to the left or to the right, we will do this for every single node we are going to insert(no copies are allowed).

FST structure provides us the ***climb*** function.

This function provides us to:

1. Move a node to the root (swap the elements) whenever his value is searched.
2. Move a node to the root whenever a node is added into the structure, if the node value already exists we move the existent node to the root.
3. When a node is removed from our structure we move his father to the root.



***Climb*** is implemented following the AVL trees rotations. Doing so we are sure we are respecting and keeping the BST structure, so all we need to implement is right rotation and left rotation.

Here is some examples about how it should work:



- How insert should work(searching should be an easier example of this )

![insert](/assets/insert.png)

- How remove should work![remove](/assets/remove.png)

