use std::io;

fn main() {
    println!("Enter the coefficients of the quadratic equation:");
    
    let mut input = String::new();
    io::stdin().read_line(&mut input).expect("Failed to read line");
    let coefficients: Vec<f64> = input
        .split_whitespace()
        .filter_map(|s| s.parse().ok())
        .collect();

    if coefficients.len() != 3 {
        println!("Please enter exactly three coefficients.");
        return;
    }

    let [a, b, c] = [coefficients[0], coefficients[1], coefficients[2]];

    let discriminant = b.powi(2) - 4.0 * a * c;

    if discriminant > 0.0 {
        let sqrt_discriminant = discriminant.sqrt();
        let denom = 2.0 * a;
        let root1 = (-b + sqrt_discriminant) / denom;
        let root2 = (-b - sqrt_discriminant) / denom;
        println!("The roots are real and distinct:");
        println!("Root 1: {}", root1);
        println!("Root 2: {}", root2);
    } else if discriminant == 0.0 {
        let root = -b / (2.0 * a);
        println!("The roots are real and equal:");
        println!("Root: {}", root);
    } else {
        let real_part = -b / (2.0 * a);
        let imaginary_part = (-discriminant).sqrt() / (2.0 * a);
        println!("The roots are complex:");
        println!("Root 1: {} + {}i", real_part, imaginary_part);
        println!("Root 2: {} - {}i", real_part, imaginary_part);
    }
}
