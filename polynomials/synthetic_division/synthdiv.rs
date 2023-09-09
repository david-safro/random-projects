
fn synthetic_division(coefficients: Vec<i32>, divisor: i32) -> (Vec<i32>, i32) {
    let n = coefficients.len();
    let mut result = Vec::with_capacity(n);
        result.push(coefficients[0]);
    
    for i in 1..n {
        result.push(result[i - 1] * divisor + coefficients[i]);
    }
        let remainder = result.last().cloned().unwrap_or(0);
        let quotient = result[..n - 1].to_vec();
    
    (quotient, remainder)
}

fn main() {
    let coefficients = vec![3, -4, 2, -1];
    let divisor = 2;
    let (quotient, remainder) = synthetic_division(coefficients, divisor);
    
    println!("Quotient: {:?}", quotient);
    println!("Remainder: {:?}", remainder);
}
