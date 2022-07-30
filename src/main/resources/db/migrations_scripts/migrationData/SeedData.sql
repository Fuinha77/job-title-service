begin
transaction;

insert into t_job_title(id, description, created_by, created_at)
values (1, 'Architect', 1, now()),
       (2, 'Software Engineer', 1, now()),
       (3, 'Quantity Surveyor', 1, now()),
       (4, 'Accountant', 1, now());

commit;