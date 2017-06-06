# HMRC shopping calculator

This is a standard SBT project, tests may be run with `sbt test`

## Assumptions
* Decided to hardcode prices of a single Apple and Organge into the implementation:
  + Would look to externalise the pricing (and move away from product values as instances of a sealed trait) before production use:
    - The product line may expand (e.g. into Pears)
    - Pricing may need to be revised on some products
  + We would not want to redeploy binaries to cater for these events.
  + Main reason for this choice was to focus on pricing calculation logic (which should be orthoganal to reference data)