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
list list_cut_below ( list head , int cut_value ) ;
/*
 * Free the list
 */
list list_delete_odd ( list head ) ;

void list_free(list p);
list list_delete_if ( list head , int to_delete ) ;
list list_dup ( list head ) ;
void stampa(list);
list list_insert_ordered ( list p , int val ) ;
int main()
{
	list a = malloc(sizeof(list)*10);
	//list b = malloc(sizeof(list)*10);
	a = list_insert_ordered(a, 1);
	a = list_insert_ordered(a, 2);
	a = list_insert_ordered(a, 3);
	a = list_insert_ordered(a, 4);
	
	printf("\n");
	
	list c = list_dup(a);
	stampa(c);
	while(c!=NULL){
	
		printf("[%d]",c->value);
		
		c = c->next;
		
	}
	
}

int length(list a){
	list b = a;
	int i=0;
	while(b!=NULL){
		i = i + 1;
	}
	return i;
}
list list_dup ( list head ){
	list a = head;
	//list res = malloc(sizeof(list)+length(a));
	list res = NULL;
	while(a!=NULL){
		res = list_insert_head(res,a->value);
		a = a->next;
	}
	return res;
}
list list_cut_below ( list head , int cut_value ){
	list res = NULL;
	list a = head;
	while(a!=NULL){
		if(a->value>=cut_value){
			res = list_insert_head(res,a->value);
		}
		a = a->next;
	}
	return res;

}
void stampa(list a){
	list b = a;
	printf("\nVettore: \n");
	while(b!=NULL){
		printf("[%d]",b->value);
		b = b->next;
	}
	printf("\n");
}
/** VARIANTE
list list_delete_odd ( list head ){
	list a = head->next;
	list res;
	int i = 0;
	while(a!=NULL){
		if(i%2==0){
			//printf("Cancella il valore %d\n",a->value);
			res = list_delete_if(a,a->value);
			stampa(res);
			
		}
		a = a->next;
		i = i + 1;
	}
	return res;
}

**/

list list_delete_odd ( list head ){
	list a = head->next;
	list res = NULL;
	int i = 0;
	while(a!=NULL){
		if(i%2!=0){
			res = list_insert_head(res,a->value);
		}
		a = a->next;
		i = i + 1;
	}
	return res;
}
list list_delete_if(list head , int to_delete){
	list a = head;
	list prev = NULL;
	int flag = 0;
	while(a!=NULL && flag == 0){
		if(a->value==to_delete){
			if(prev == NULL){
				a = a->next;
			}else{
				prev->next = a->next;
				
			}
			printf("Il valore %d è stato eliminato\n",a->value);
			flag = 1;
		}
		prev = a;
		a = a->next;
	}
	return head;



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
