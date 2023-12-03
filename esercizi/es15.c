#include <stdio.h>
#include <stdlib.h>

typedef struct node {
	int value;
	struct node * next;   /* pointer to the same structure */
} node;

typedef node* list;

/*
 * Assume that the list is in increasing order and insert the element
 * preserving the increasing order
 */
list list_insert_ordered(list p, int val);

/*
 * Concatenate two lists
 */
list list_cat(list before, list after);

/*
 * Insert elements at the head of the list
 */
list list_insert_head(list p, int val);

/*
 * Insert elements at the tail of the list
 */
list list_insert_tail(list p, int val);

/*
 * Print the list content
 */
void list_print(list p);

/*
 * Free the list
 */

void list_free(list p);



list list_insert_ordered ( list p , int val ) ;
int main()
{
	list a = malloc(sizeof(list)*10);
	//list b = malloc(sizeof(list)*10);
	list_insert_ordered(a, 1);
	//list_insert_ordered(b, 1);
	list c = list_insert_tail(a,30);
	
	while(c!=NULL){
		printf("[%d]",c->value);
		c = c->next;
	}
}

list list_cat ( list before , list after ){
	list a = before;
	while(a->next!=NULL){
		a = a->next;
	}
	a->next = after;
	return before;

}

list list_insert_tail ( list p , int val ){
	list a = p;
	while(a->next!=NULL){
		a = a->next;
	}
	list temp  = malloc(sizeof(list));
	temp->value=val;
	a->next = temp;
	return p;
}

list list_insert_ordered(list p, int val){
	list a = p;
	int flag = 0;
	while(a!=NULL && flag == 0){
		if(val>a->value){
			/*printf("Current node:\n");
			printf("Value: %d\n",a->value);
			printf("Next: %p\n\n",a->next);*/
			
			list temp = a->next;
			temp = list_insert_head(temp,val);
			a->next = temp;
			flag = 1;
		}
		a = a->next;
	}
	return p;
}

list list_insert_head(list p, int val)
{
	list new_el;

	/* Allocate the new node */
	new_el = malloc(sizeof(*new_el));
	/* Setting the data */
	new_el->value = val;
	/* head insertion: old head becomes .next field of new head */
	new_el->next = p;
	/* new head is the pointer to the new node */
	return new_el;
}

void list_print(list p)
{
	/* Looping all nodes until p == NULL */
	if (p == NULL) {
		printf("Empty list\n");
		return;
	}
	printf("[%i]", p->value);
	for(; p->next!=NULL; p = p->next) {
		printf(" -> [%i]", p->next->value);
	}
	printf("\n");
}

void list_free(list p)
{
	/* If p ==  NULL, nothing to deallocate */
	if (p == NULL) {
		return;
	}
	/* First deallocate (recursively) the next nodes... */
	list_free(p->next);
	/* ... then deallocate the node itself */
	free(p);
}
