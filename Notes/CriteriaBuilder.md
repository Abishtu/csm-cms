Using criteria builder for filter params instead of raw queries:
```java
// Source - https://stackoverflow.com/a/50569466
// Posted by Ilya Dyoshin
// Retrieved 2026-01-01, License - CC BY-SA 4.0

// Actually can be generated during build, and thus can be ommited
@StaticMetamodel(Moneda.class)
abstract class Moneda_ {
    public static volatile SingularAttribute<Moneda, BigDecimal> cantidad;
    public static volatile SingularAttribute<Moneda, Divisia> divisia;
    public static volatile SingularAttribute<Moneda, BigDecimal> ano;
}

final CriteriaBuilder cb = em.getCriteriaBuilder();

final CriteriaQuery<Moneda> cq = cb.createQuery(Moneda.class);
final Root<Moneda> root = cq.from(Moneda.class);

Set<Predicate> predicates = new HashSet<>(3);
    if (cantidad != null) {
        predicates.add(cb.equal(root.get(Moneda_.cantidad), cantidad));
        }

        if (ano != null) {
        predicates.add(cb.equal(root.get(Moneda_.ano), ano));
        }

        if (divisia != null) {
        predicates.add(cb.equal(root.get(Moneda_.divisia), divisia));
        }

        cq.where(predicates.toArray(new Predicate[predicates.size()]));

        em.createQuery(cq).getResultList();

// and do whatever you want 

```