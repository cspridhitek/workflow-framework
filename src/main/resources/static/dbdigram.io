Table user {
  id UUID [pk]
  username varchar
  email varchar [unique, not null]
  status varchar
  created_at datetime
  updated_at datetime
}

Table role {
  id bigint [pk]
  name varchar
  description varchar
  created_at datetime
  updated_at datetime
}

Table permission {
  id bigint [pk]
  name varchar
  description varchar
  created_at datetime
  updated_at datetime
}

Table user_role {
  id bigint [pk]
  user_id UUID [ref: > user.id]
  role_id bigint [ref: > role.id]
  created_at datetime
  updated_at datetime
}

Table role_permission {
  id bigint [pk]
  role_id bigint [ref: > role.id]
  permission_id bigint [ref: > permission.id]
  created_at datetime
  updated_at datetime
}

Table workflow {
  id UUID [pk]
  name varchar
  description varchar
  created_at datetime
  updated_at datetime
}

Table workflow_version {
  id bigint [pk]
  workflow_id UUID [ref: > workflow.id]
  version varchar
  description varchar
  is_active boolean
  created_at datetime
  updated_at datetime
}

Table workflow_step {
  id bigint [pk]
  workflow_id UUID [ref: > workflow.id]
  name varchar
  step_order int
  is_mandatory boolean
  created_at datetime
  updated_at datetime
}

Table workflow_task {
  id bigint [pk]
  step_instance_id bigint [ref: > workflow_step.id]
  assigned_to UUID [ref: > user.id]
  status varchar
  due_date datetime
  completed_at datetime
  created_at datetime
  updated_at datetime
}

Table workflow_instance {
  id bigint [pk]
  workflow_version_id bigint [ref: > workflow_version.id]
  entity_id bigint
  status varchar
  created_at datetime
  updated_at datetime
}

Table workflow_instance_step {
  id bigint [pk]
  instance_id bigint [ref: > workflow_instance.id]
  step_id bigint [ref: > workflow_step.id]
  status varchar
  started_at datetime
  ended_at datetime
  created_at datetime
  updated_at datetime
}