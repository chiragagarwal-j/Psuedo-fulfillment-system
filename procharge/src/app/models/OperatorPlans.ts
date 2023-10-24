export interface Plan {
    name: string;
    type: string;
}

export interface OperatorPlans {
    name: string;
    services: Plan[];
}